package com.example.admin.appsqliteqlsv.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.appsqliteqlsv.R;
import com.example.admin.appsqliteqlsv.model.Students;

import java.util.List;

/**
 * Created by Admin on 29/12/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Students> liststudent;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Students> objects) {
        super(context, resource,objects);
        this.context = context;
        this.resource = resource;
        this.liststudent= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.tvEmail = (TextView) convertView.findViewById(R.id.tv_email);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Students students = liststudent.get(position);//vi tri cua student dau tien
        viewHolder.tvId.setText(students.getId()+"");//id kieu int phai convert qua String
        viewHolder.tvName.setText(students.getName());
        viewHolder.tvNumber.setText(students.getNumber());
        viewHolder.tvEmail.setText(students.getEmail());
        viewHolder.tvAddress.setText(students.getAddress());



        return convertView;
    }
    public class ViewHolder{
        //tranh truong hop qua nhieu item cho nen su dung viewholder de khong bi lag
        private TextView tvId;
        private TextView tvName;
        private TextView tvNumber;
        private TextView tvEmail;
        private TextView tvAddress;
    }
}
