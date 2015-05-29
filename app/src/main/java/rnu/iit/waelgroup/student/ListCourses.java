package rnu.iit.waelgroup.student;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rnu.iit.waelgroup.student.Adapters.ClickableListAdapter;
import rnu.iit.waelgroup.student.Models.Course;


public class ListCourses extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener {

    public ArrayList<Course> courses = new ArrayList<Course>() ;
    private AbsListView mListView;
    private MyClickableListAdapter mAdapter ;
    private TextView empty;
    private SimpleCursorAdapter adapt;

    public static boolean isDownloadManagerAvailable(Context context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
        empty = (TextView) findViewById(R.id.emptyCourses);
        empty.setVisibility(View.INVISIBLE);
        String subject;
        Bundle  extras = getIntent().getExtras();
        if(extras == null) {
        } else {
            subject=  extras.getString("subject");
            Toast.makeText(getApplicationContext(), subject, Toast.LENGTH_LONG).show();
            int i=0;
            for (Course c : CoursesFragment.courses){
                if (c.getSubject().trim().equals(subject.trim())){
                    courses.add(c);
                    i++;
                }
            }
            if (i==0){
                empty.setVisibility(View.VISIBLE);
            }
            }

        mAdapter = new MyClickableListAdapter(getApplicationContext(),R.layout.item_course,courses);
        mListView = (AbsListView) findViewById(R.id.listCourses);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        ////////////////////////////////////////////    provider test work ////////////////////////////////////////////////////////

/*
        final String[] projection = {myCustomProvider.ITEM_ID,myCustomProvider.COURSE, myCustomProvider.DAT};

        final String[] uiBindFrom = {myCustomProvider.COURSE,  myCustomProvider.DAT};

        final int[] uiBindTo = {R.id.course_name, R.id.date_depo_course};

        Cursor cursor = getContentResolver().query(myCustomProvider.CONTENT_URI, projection,
                null, null, null);

        adapt = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.item_course,
                cursor, uiBindFrom, uiBindTo,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        mListView.setAdapter(adapt);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_courses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {myCustomProvider.ITEM_ID, myCustomProvider.COURSE, myCustomProvider.DAT
                };

        return new CursorLoader(this,
                myCustomProvider.CONTENT_URI, projection, null, null, null);
    }

        @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            adapt.swapCursor(data);

        }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapt.swapCursor(null);
    }

    static class MyViewHolder extends ClickableListAdapter.ViewHolder {

        Button view, download;
        TextView name, date;
        public MyViewHolder( TextView name ,  TextView date ,Button view, Button download) {
            name = this.name;
            date = this.date;
            download = this.download;
            view = this.view;
        }
    }

    private class MyClickableListAdapter extends ClickableListAdapter {
        public MyClickableListAdapter(Context context, int viewid,
                                      List<Course> objects) {
            super(context, viewid, objects);
            // nothing to do
        }

        protected void bindHolder(ViewHolder h) {
            // Binding the holder keeps our data up to date.
            // In contrast to createHolder this method is called for all items
            // So, be aware when doing a lot of heavy stuff here.
            // we simply transfer our object's data to the list item representatives
            MyViewHolder mvh = (MyViewHolder) h;
          }

        @Override
        protected ViewHolder createHolder(View v, final int position) {
            // createHolder will be called only as long, as the ListView is not filled
            // entirely. That is, where we gain our performance:
            // We use the relatively costly findViewById() methods and
            // bind the view's reference to the holder objects.
            TextView name = (TextView) v.findViewById(R.id.course_name);
            TextView date = (TextView) v.findViewById(R.id.date_depo_course);
            Button view_btn = (Button)v.findViewById(R.id.view_course);
            Button download_btn = (Button)v.findViewById(R.id.download_course);
            name.setText(courses.get(position).getName());
            date.setText(courses.get(position).getDateDepo());
            ViewHolder mvh = new MyViewHolder(name, date, view_btn, download_btn);

            // Additionally, we make some icons clickable
            // Mind, that item becomes clickable, when adding a click listener (see API)
            // so, it is not necessary to use the android:clickable attribute in XML
            view_btn.setOnClickListener(new ClickableListAdapter.OnClickListener(mvh) {

                public void onClick(View v, ViewHolder viewHolder) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(courses.get(position).getUrl()));
                    startActivity(browserIntent);
                    Toast.makeText(getApplicationContext(), "test View button " + position, Toast.LENGTH_LONG).show();
                }
            });

            download_btn.setOnClickListener(new ClickableListAdapter.OnClickListener(mvh) {
                public void onClick(View v, ViewHolder viewHolder) {

                    String url = courses.get(position).getUrl();
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setDescription("" + courses.get(position).getDescription());
                    request.setTitle("" + courses.get(position).getName());
                    // in order for this if to run, you must use the android 3.2 to compile your app
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");
                    // get download service and enqueue file
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    // Toast.makeText(getApplicationContext(), "test download button ", Toast.LENGTH_LONG).show();
                }
            });
            return mvh; // finally, we return our new holder
        }
    }
}
