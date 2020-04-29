package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.BaseCheckBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BaseCheckAdapter
 * 描述：
 * 创建时间：2019-11-27  09:27
 */

public class BaseCheckAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TXT = 1;
    private final int EDIT = 2;
    private final int CHOOSE = 3;
    private final int INVOINCE = 4;
    private final int FOOT = 5;
    private final int TITLE = 6;

    private OnPositionCallBack choosePositionCallBack;
    private Context context;
    private List<BaseCheckBean> list;

    public void setFootView(View footView) {
        this.footView = footView;
    }

    private View footView;

    public BaseCheckAdapter(Context context, List<BaseCheckBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
//        switch (list.get(position).getType()) {
//            case TXT:
//                return TXT;
//            case EDIT:
//                return EDIT;
//            case CHOOSE:
//                return EDIT;
//            default:
//                return TXT;
//        }
        if (position == getItemCount() - 1) {
            return FOOT;
        }
        if (list.get(position).getType() == TXT) {
            return TXT;
        }
        if (list.get(position).getType() == EDIT) {
            return EDIT;
        }
        if (list.get(position).getType() == CHOOSE) {
            return CHOOSE;
        }
        if (list.get(position).getType() == INVOINCE) {
            return INVOINCE;
        }
        if (list.get(position).getType() == TITLE) {
            return TITLE;
        }
        return TXT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TXT) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base_check_txt, null);
            return new TxtViewHolder(view);
        } else if (viewType == EDIT) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base_check_edit, null);
            return new EditViewHolder(view);
        } else if (viewType == CHOOSE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base_check_choose, null);
            return new ChooseViewHolder(view);
        } else if (viewType == INVOINCE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base_check_invoince, null);
            return new InvoinceHolder(view);
        } else if (viewType == TITLE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_base_check_title, null);
            return new TitleHolder(view);
        } else if (viewType == FOOT) {
            return new FootViewHolder(footView);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_base_check_choose, null);
            return new ChooseViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TxtViewHolder) {
            TxtViewHolder txtViewHolder = (TxtViewHolder) holder;
            txtViewHolder.tvLeftTxt.setText(list.get(position).getHintType());
            if (list.get(position).getEditType() == 1) {
                txtViewHolder.tvContent.setText(MoneyUtils.getInstance().getFormPrice(list.get(position).getContent()));
            } else {
                txtViewHolder.tvContent.setText(list.get(position).getContent());
            }
        } else if (holder instanceof EditViewHolder) {
            EditViewHolder editViewHolder = (EditViewHolder) holder;
            editViewHolder.tvLeftEdit.setText(list.get(position).getHintType() + "：");
            EditText etContent = editViewHolder.etContent;
            //文本监听
            etContent.clearFocus();
            switch (list.get(position).getEditType()) {
                case 1:
                    etContent.setInputType(InputType.TYPE_CLASS_PHONE);
                    break;
                case 2:
                    etContent.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
            }

            if (etContent.getTag() instanceof TextWatcher) {
                etContent.removeTextChangedListener((TextWatcher) (etContent.getTag()));
            }
            //文本改变监听
            final TextWatcher oneNameWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (TextUtils.isEmpty(editable)) {
                        list.get(position).setContent("");
                    } else {
                        list.get(position).setContent(StringUtils.getNoSapceStr(String.valueOf(editable)));
                    }
                }
            };
            //吧监听设置到不同的EditText上
            etContent.addTextChangedListener(oneNameWatcher);
            etContent.setTag(oneNameWatcher);


            String content = "";
            if(StringUtils.isNoEmpty(list.get(position).getContent()) && list.get(position).getContent().equals("0.0")){
                content = "0";
            }else {
                content = list.get(position).getContent();
            }

//            LogUtils.e(content+"");
            etContent.setText(content);
            if (list.get(position).isHide()) {
                editViewHolder.rlAddress.setVisibility(View.GONE);
            } else {
                editViewHolder.rlAddress.setVisibility(View.VISIBLE);
            }

        } else if (holder instanceof ChooseViewHolder) {
            ChooseViewHolder txtViewHolder = (ChooseViewHolder) holder;
            txtViewHolder.tvLeftChoose.setText(list.get(position).getHintType());
            if (list.get(position).getHintType().contains("日期")) {
                if (StringUtils.isNoEmpty(list.get(position).getContent()) && list.get(position).getContent().contains("Date")) {
                    String time = DateUtils.getFormDesData(list.get(position).getContent());
                    list.get(position).setContent(time);
                } else {
                    list.get(position).setContent(list.get(position).getContent());
                }
                txtViewHolder.tvRightChoose.setText(list.get(position).getContent());
            } else if (list.get(position).getHintType().contains("发票")) {
                switch (list.get(position).getContent()) {
                    case "0":
                        txtViewHolder.tvRightChoose.setText("未提交发票");
                        break;
                    case "1":
                        txtViewHolder.tvRightChoose.setText("有发票");
                        break;
                    default:
                        txtViewHolder.tvRightChoose.setText("未提交发票");
                        break;
                }
            } else {
                txtViewHolder.tvRightChoose.setText(list.get(position).getContent());
            }
//            txtViewHolder.tvRightChoose.setText(StringUtils.getNoNullChooseStr(list.get(position).getContent()) + "  ");

            txtViewHolder.tvRightChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choosePositionCallBack.onPositionClick(position);
                }
            });
            if (list.get(position).isHide()) {
                txtViewHolder.rlUseTime.setVisibility(View.GONE);
            } else {
                txtViewHolder.rlUseTime.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof TitleHolder) {
            TitleHolder txtViewHolder = (TitleHolder) holder;
            txtViewHolder.tvTitle.setText(list.get(position).getHintType());
            //2.4  2760862 wenyan01
        } else if (holder instanceof InvoinceHolder) {
            InvoinceHolder invoinceHolder = (InvoinceHolder) holder;
            invoinceHolder.tvLeftInvoince.setText(list.get(position).getHintType());
            invoinceHolder.cbInvoince.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    list.get(position).setContent(b ? "1" : "0");
                }
            });
            switch (list.get(position).getContent()) {
                case "1":
                    invoinceHolder.cbInvoince.setChecked(true);
                    break;
                case "0":
                    invoinceHolder.cbInvoince.setChecked(false);
                    break;
            }
            if (list.get(position).isHide()) {
                invoinceHolder.rlInvoince.setVisibility(View.GONE);
            } else {
                invoinceHolder.rlInvoince.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;


        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TxtViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_left_txt)
        TextView tvLeftTxt;
        @Bind(R.id.tv_content)
        TextView tvContent;

        public TxtViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class InvoinceHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_invoince_left)
        TextView tvLeftInvoince;
        @Bind(R.id.cb_invoice)
        CheckBox cbInvoince;
        @Bind(R.id.rl_invoince)
        RelativeLayout rlInvoince;

        public InvoinceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EditViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_left_edit)
        TextView tvLeftEdit;
        @Bind(R.id.et_address)
        EditText etContent;
        @Bind(R.id.rl_address)
        RelativeLayout rlAddress;

        public EditViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ChooseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_choose_left)
        TextView tvLeftChoose;
        @Bind(R.id.tv_choose_right)
        TextView tvRightChoose;
        @Bind(R.id.rl_use_time)
        RelativeLayout rlUseTime;

        public ChooseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setChoosePositionCallBack(OnPositionCallBack choosePositionCallBack) {
        this.choosePositionCallBack = choosePositionCallBack;
    }
}
