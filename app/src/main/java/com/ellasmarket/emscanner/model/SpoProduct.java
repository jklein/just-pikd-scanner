package com.ellasmarket.emscanner.model;

/**
 * Created by Scott on 3/5/15.
 */
import java.util.Date;

/**
 * Created by Scott on 3/3/15.
 */

public class SpoProduct {
    public int spop_id;
    public int spop_spo_id;
    public String spop_pr_sku;
    public String spop_rcl_id;
    public String spop_status;
    public int spop_requested_qty;
    public Integer spop_confirmed_qty;
    public Integer spop_received_qty;
    public String spop_case_upc;
    public Integer spop_units_per_case;
    public Integer spop_requested_case_qty;
    public Integer spop_confirmed_case_qty;
    public Integer spop_received_case_qty;
    public Double spop_case_length;
    public Double spop_case_width;
    public Double spop_case_height;
    public Double spop_case_weight;
    public Date spop_expected_arrival;
    public Date spop_actual_arrival;
    public Integer spop_wholesale_cost;
    public String spop_expiration_class;
    public int spop_ma_id;
}

