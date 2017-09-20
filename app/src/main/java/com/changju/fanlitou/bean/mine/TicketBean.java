package com.changju.fanlitou.bean.mine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chengww on 2017/7/28.
 */

public class TicketBean implements Parcelable{

    private int ticket_id;
    private int order_id;
    private String name;

    protected TicketBean(Parcel in) {
        ticket_id = in.readInt();
        order_id = in.readInt();
        name = in.readString();
    }

    public TicketBean(int ticket_id, int order_id, String name) {
        this.ticket_id = ticket_id;
        this.order_id = order_id;
        this.name = name;
    }

    public static final Creator<TicketBean> CREATOR = new Creator<TicketBean>() {
        @Override
        public TicketBean createFromParcel(Parcel in) {
            return new TicketBean(in);
        }

        @Override
        public TicketBean[] newArray(int size) {
            return new TicketBean[size];
        }
    };

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ticket_id);
        dest.writeInt(order_id);
        dest.writeString(name);
    }
}
