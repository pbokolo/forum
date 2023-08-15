package com.kitoko.forum.controllers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.kitoko.forum.R;
import com.kitoko.forum.business.Subject;

public class Subjects extends FirestoreRecyclerAdapter<Subject, Subjects.SubjectViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Subjects(@NonNull FirestoreRecyclerOptions<Subject> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectViewHolder holder, int position, @NonNull Subject model) {
        holder.render(model);
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View subjectVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_subject, null);
        return new SubjectViewHolder(subjectVH);
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        private TextView subjectLbl, detailsLbl;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectLbl = itemView.findViewById(R.id.subjectLbl);
            detailsLbl = itemView.findViewById(R.id.detailsLbl);
        }

        void render(Subject subject){
            subjectLbl.setText(subject.getTitle());
            detailsLbl.setText(subject.getUid());
        }
    }

}
