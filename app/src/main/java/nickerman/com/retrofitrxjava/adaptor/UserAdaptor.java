package nickerman.com.retrofitrxjava.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nickerman.com.retrofitrxjava.R;
import nickerman.com.retrofitrxjava.model.User;


public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.ViewHolder>{

    private List<User> listUser = new ArrayList<>();

    public UserAdaptor(List<User> listUser) {
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = listUser.get(position);

        holder.userId.setText(String.valueOf(user.userId));
        holder.title.setText(String.valueOf(user.title));
        holder.body.setText(new StringBuffer(user.body.substring(0,16)).append("...").toString());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        private TextView userId ,title, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.userId = itemView.findViewById(R.id.user_id);
            this.title = itemView.findViewById(R.id.title);
            this.body = itemView.findViewById(R.id.body);
        }
    }
}
