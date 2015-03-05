package com.ellasmarket.emscanner.model;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Scott on 3/5/15.
 */
public class Spo {
    public int spo_id;
    public String spo_status;
    public int spo_su_id;
    public Date spo_date_ordered;
    public Date spo_date_confirmed;
    public Date spo_date_shipped;
    public Date spo_date_arrived;
    public ArrayList<SpoProduct> products;
}
