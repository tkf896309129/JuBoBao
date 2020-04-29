package com.example.huoshangkou.jubowan.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.chat.ChatConstant;
import com.example.huoshangkou.jubowan.db.ChatBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatRecylerAdapter
 * 描述：
 * 创建时间：2020-04-07  15:18
 */

public class ChatRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int OTHER_MESSAGE = 1;
    private final int ME_MESSAGE = 2;
    private Context context;
    private List<ChatBean> chatBeanList;
    private List<String> imgList;

    public ChatRecylerAdapter(Context context, List<ChatBean> chatBeanList,List<String> imgList) {
        this.context = context;
        this.chatBeanList = chatBeanList;
        this.imgList = imgList;
    }

    @Override
    public int getItemViewType(int position) {
        return chatBeanList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ChatConstant.CHAT_MESSAGE_OTHER:
                view = LayoutInflater.from(context).inflate(R.layout.item_chat_other_message, null);
                return new OtherMessageViewHolder(view);
            case ChatConstant.CHAT_MESSAGE_MINE:
                view = LayoutInflater.from(context).inflate(R.layout.item_me_chat_message, null);
                return new MeMessageViewHolder(view);
            case ChatConstant.CHAT_GALLERY_MINE:
                view = LayoutInflater.from(context).inflate(R.layout.item_chat_gallery_mine, null);
                return new ImageViewHolder(view);
            case ChatConstant.CHAT_GALLERY_OTHER:
                view = LayoutInflater.from(context).inflate(R.layout.item_chat_gallery_other, null);
                return new ImageOtherViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ChatBean chatBean = chatBeanList.get(position);
        if (holder == null) {
            return;
        }
        if (holder instanceof OtherMessageViewHolder) {
            OtherMessageViewHolder otherMessageViewHolder = (OtherMessageViewHolder) holder;
            otherMessageViewHolder.tvContent.setText(chatBean.getContent());
        } else if (holder instanceof MeMessageViewHolder) {
            MeMessageViewHolder meMessageViewHolder = (MeMessageViewHolder) holder;
            meMessageViewHolder.tvContent.setText(chatBean.getContent());
        } else if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
//            Glide.with(context)
//                    .load(chatBean.getContent())
//                    .transform(new GlideRoundTransform(context,8))
//                    .into(new TransformationUtils(imageViewHolder.ivContent, chatBean.getHeight(), chatBean.getWidth()));
            imageViewHolder.ivContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtils.getInstance().toImageShowActivity(context,imgList,chatBean.getImgPosition());
                }
            });
        } else if (holder instanceof ImageOtherViewHolder) {
            ImageOtherViewHolder imageViewHolder = (ImageOtherViewHolder) holder;
//            Glide.with(context)
//                    .load(chatBean.getContent())
//                    .transform(new GlideRoundTransform(context,8))
//                    .into(new TransformationUtils(imageViewHolder.ivContent, chatBean.getHeight(), chatBean.getWidth()));
            imageViewHolder.ivContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtils.getInstance().toImageShowActivity(context,imgList,chatBean.getImgPosition());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return chatBeanList == null ? 0 : chatBeanList.size();
    }

    class OtherMessageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHead;
        TextView tvContent;

        public OtherMessageViewHolder(View itemView) {
            super(itemView);
            imgHead = itemView.findViewById(R.id.iv_head);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }

    class MeMessageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHead;
        TextView tvContent;

        public MeMessageViewHolder(View itemView) {
            super(itemView);
            imgHead = itemView.findViewById(R.id.iv_head);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHead;
        ImageView ivContent;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imgHead = itemView.findViewById(R.id.iv_head);
            ivContent = itemView.findViewById(R.id.iv_content);
        }
    }

    class ImageOtherViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHead;
        ImageView ivContent;

        public ImageOtherViewHolder(View itemView) {
            super(itemView);
            imgHead = itemView.findViewById(R.id.iv_head);
            ivContent = itemView.findViewById(R.id.iv_content);
        }
    }
}
