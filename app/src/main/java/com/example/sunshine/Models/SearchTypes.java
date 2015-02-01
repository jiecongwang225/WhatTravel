package com.example.sunshine.Models;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class SearchTypes  {


    private List<String> types = Lists.newArrayList();


    public void addType(String type) {
       types.add(type);
    }

    public String toString() {
        return types.size()>1? Joiner.on("|").join(types):types.get(0);
    }

}
