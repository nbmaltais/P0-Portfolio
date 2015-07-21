package ca.nbmaltais.portfolio;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Nicolas on 2015-07-20.
 */
public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder>
{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    static public class Project{
        public final String name;
        public final String description;
        Intent lauchIntent=null;

        static public Project newInstance(Context context, @StringRes int nameResId, @StringRes int descriptionResId)
        {
            return new Project( context.getString(nameResId), context.getString(descriptionResId) );
        }

        public Project(String name, String description)
        {
            this.name = name;
            this.description = description;
        }
    }

    static public class ViewHolder extends RecyclerView.ViewHolder
    {
        Project mProject;
        TextView projectNameView;
        TextView projectDescriptionView;
        public ViewHolder(View v)
        {
            super(v);
            projectNameView = (TextView)v.findViewById(R.id.project_name);
            projectDescriptionView = (TextView)v.findViewById(R.id.project_description);

            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context ctx= v.getContext();
                    if(mProject.lauchIntent==null)
                    {
                        Toast.makeText(ctx, ctx.getString(R.string.project_clicked, mProject.name), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ctx.startActivity(mProject.lauchIntent);
                    }
                }
            });
        }

        public void bind( Project project )
        {
            mProject=project;
            projectNameView.setText(project.name);
            projectDescriptionView.setText(project.description);
        }
    }

    private List<Project> mItems;

    public ProjectsAdapter(List<Project> projects)
    {
        mItems=projects;
    }




}
