package f.star.iota.milk.ui.download;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;

import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.lzy.okserver.OkDownload;

import java.io.File;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.config.ThemeConfig;
import f.star.iota.milk.util.MessageBar;

public class DownloadManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.floating_action_button)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.floating_toolbar)
    FloatingToolbar mFloatingToolbar;
    @BindView(R.id.recycler_view_download_record)
    RecyclerView mRecyclerView;
    private DownloadAdapter mDownloadAdapter;
    private OkDownload mOkDownload;

    @Override
    protected void init() {
        create();
        initRecyclerView();
        initEvents();
    }

    private void initRecyclerView() {
        mDownloadAdapter = new DownloadAdapter();
        mDownloadAdapter.updateData(DownloadAdapter.TYPE_ALL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mDownloadAdapter);
    }

    private void initEvents() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FloatingToolbar.ItemClickListener itemClickListener = new FloatingToolbar.ItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                if (mOkDownload == null || mDownloadAdapter == null) return;
                switch (item.getItemId()) {
                    case R.id.action_start_all:
                        mOkDownload.startAll();
                        break;
                    case R.id.action_pause_all:
                        mOkDownload.pauseAll();
                        break;
                    case R.id.action_clear_all:
                        new AlertDialog.Builder(mContext)
                                .setIcon(R.mipmap.app_icon)
                                .setTitle("清空列表")
                                .setNegativeButton("嗯", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mOkDownload.removeAll();
                                        mDownloadAdapter.updateData(DownloadAdapter.TYPE_ALL);
                                    }
                                })
                                .setNeutralButton("还是算了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();
                        break;
                    case R.id.action_delete_all:
                        new AlertDialog.Builder(mContext)
                                .setIcon(R.mipmap.app_icon)
                                .setTitle("清空列表并删除文件")
                                .setNegativeButton("嗯", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mOkDownload.removeAll(true);
                                        mDownloadAdapter.updateData(DownloadAdapter.TYPE_ALL);
                                    }
                                })
                                .setNeutralButton("还是算了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();
                        break;
                }
            }

            @Override
            public void onItemLongClick(MenuItem item) {

            }
        };
        mFloatingToolbar.setClickListener(itemClickListener);
        mDownloadAdapter.setOnOpenListener(new DownloadAdapter.OnOpenListener() {
            @Override
            public void open(String filePath) {
                if (filePath == null || filePath.length() < 2) return;
                File file = new File(filePath);
                if (file.exists()) {
                    MimeTypeMap mime = MimeTypeMap.getSingleton();
                    String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                    String type = mime.getMimeTypeFromExtension(ext);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        Uri contentUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", file);
                        intent.setDataAndType(contentUri, type);
                    } else {
                        intent.setDataAndType(Uri.fromFile(file), type);
                    }
                    startActivity(intent);
                } else {
                    MessageBar.create(mContext, "文件不存在，是否被删除？");
                }
            }
        });
        mDownloadAdapter.setOnSetBannerListener(new DownloadAdapter.OnSetBannerListener() {
            @Override
            public void set(final String filePath) {
                new AlertDialog.Builder(mContext)
                        .setTitle("设置为侧滑栏BANNER")
                        .setNegativeButton("嗯", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setBanner(filePath);
                            }
                        })
                        .setPositiveButton("还是算了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    private void setBanner(String path) {
        ThemeConfig.saveBanner(aContext, "file://" + path);
        MessageBar.create(mContext, "主界面重启后生效");
    }

    private void create() {
        setSupportActionBar(mToolbar);
        mFloatingToolbar.attachFab(mFloatingActionButton);
        mFloatingToolbar.attachRecyclerView(mRecyclerView);
        mOkDownload = OkDownload.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download_manager_show_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_all:
                mDownloadAdapter.updateData(DownloadAdapter.TYPE_ALL);
                break;
            case R.id.action_show_ing:
                mDownloadAdapter.updateData(DownloadAdapter.TYPE_ING);
                break;
            case R.id.action_show_finished:
                mDownloadAdapter.updateData(DownloadAdapter.TYPE_FINISH);
                break;
        }
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_download_manager;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDownloadAdapter != null) {
            mDownloadAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDownloadAdapter != null) {
            mDownloadAdapter.unRegister();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
