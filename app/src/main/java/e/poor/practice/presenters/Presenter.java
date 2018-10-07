package e.poor.practice.presenters;

import android.content.Context;
import android.util.Log;

import java.util.List;

import e.poor.practice.databases.Database;
import e.poor.practice.models.Category;

public class Presenter {

    private Context mContext;

    public Presenter(Context context) {
        this.mContext = context;
    }

    public List<Category> getList() {
        List<Category> list = Database.getInstance(mContext).categoryDAO().getAllCategory();
        Log.i("database", "List: " +list.size());
        return list == null ? null : list;
    }
}
