package xstream.POJO;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Bill
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/3 11:52
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/3        shilun           v1.0.0               创建
 */
@XStreamAlias("bill")
public class Bill {

    @XStreamAlias("id")
    @XStreamAsAttribute
    private String id;

    @XStreamAlias("billhead")
    private BillHead billHead;

    @XStreamAlias("billhead")
    private Custsaleclass custsaleclass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BillHead getBillHead() {
        return billHead;
    }

    public void setBillHead(BillHead billHead) {
        this.billHead = billHead;
    }

    public Custsaleclass getCustsaleclass() {
        return custsaleclass;
    }

    public void setCustsaleclass(Custsaleclass custsaleclass) {
        this.custsaleclass = custsaleclass;
    }
}
