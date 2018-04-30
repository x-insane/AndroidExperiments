package com.xinsane.chat.main.session;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.xinsane.chat.R;
import com.xinsane.chat.chat.ChatActivity;
import com.xinsane.chat.main.MainActivity;
import com.xinsane.chat.main.session.item.Item;

import java.util.List;

public class SessionListAdapter extends RecyclerView.Adapter<SessionListAdapter.ViewHolder> {

    private MainActivity activity;
    private List<Item> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public SessionListAdapter(List<Item> list, MainActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).resource();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        final ViewHolder holder = new ViewHolder(view);

//        // 弹出式上下文菜单
//        view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//                if (holder.getItemViewType() != R.layout.list_item_session)
//                    return;
//                contextMenu.add(Menu.NONE, 1, 1, "标为未读")
//                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        return false;
//                    }
//                });
//                contextMenu.add(Menu.NONE, 2, 2, "置顶聊天");
//                contextMenu.add(Menu.NONE, 3, 3, "删除该聊天");
//            }
//        });

//        // 自定义触摸效果
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        if (holder.getItemViewType() == R.layout.list_item_session)
//                            view.findViewById(R.id.layout_item_session).setBackgroundColor(0xffd9d9d9);
//                        else if (holder.getItemViewType() == R.layout.list_item_session_tip)
//                            view.findViewById(R.id.layout_item_session_tip).setBackgroundColor(0xffd9d9d9);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_POINTER_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                    case MotionEvent.ACTION_BUTTON_RELEASE:
//                        if (holder.getItemViewType() == R.layout.list_item_session)
//                            view.findViewById(R.id.layout_item_session).setBackgroundColor(0xffffffff);
//                        else if (holder.getItemViewType() == R.layout.list_item_session_tip)
//                            view.findViewById(R.id.layout_item_session_tip).setBackgroundColor(0xfff5f5f5);
//                        break;
//                    case MotionEvent.ACTION_BUTTON_PRESS:
//                        view.performClick();
//                        break;
//                }
//                return false;
//            }
//        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getItemViewType() == R.layout.list_item_session) {
                    int position = holder.getAdapterPosition();
                    activity.startActivityForResult(new Intent(activity, ChatActivity.class), 1);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        list.get(position).sync(holder.view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
