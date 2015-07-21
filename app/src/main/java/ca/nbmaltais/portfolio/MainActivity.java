package ca.nbmaltais.portfolio;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProjectsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        //mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        List<ProjectsAdapter.Project> projects = new ArrayList<>();

        projects.add( ProjectsAdapter.Project.newInstance(this,R.string.popular_movies,R.string.popular_movies_description));
        projects.add( ProjectsAdapter.Project.newInstance(this,R.string.alexandria,R.string.alexandria_description));
        projects.add( ProjectsAdapter.Project.newInstance(this,R.string.football_scores,R.string.football_scores_description));
        projects.add( ProjectsAdapter.Project.newInstance(this,R.string.build_it_bigger,R.string.build_it_bigger_description));
        projects.add( ProjectsAdapter.Project.newInstance(this,R.string.xyz_reader,R.string.xyz_reader_description));
        projects.add(ProjectsAdapter.Project.newInstance(this, R.string.capstone, R.string.capstone_description));

        mAdapter = new ProjectsAdapter(projects);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        final View appbar = findViewById(R.id.appbar);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int max = appbar.getHeight();
                        if (dy > 0) {
                            appbar.setTranslationY(Math.max(-max, appbar.getTranslationY() - dy / 2));
                        } else {
                            appbar.setTranslationY(Math.min(0, appbar.getTranslationY() - dy / 2));
                        }
                    }
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
