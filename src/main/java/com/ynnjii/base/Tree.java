package com.ynnjii.base;

import java.util.List;

/**
 * project：ynnjii-web
 * class：tree
 * author：isaac
 * date：2017/5/25
 * description：
 */
public interface Tree<T> {
    Object getIdz();

    Object getParentId();

    List<? extends Tree> getChild();

    void addChild(Tree tree);

    default boolean ifHasChild(){
        return getChild().size()!=0;
    }

    default boolean isSubOf(Tree parent){
        return getParentId().toString().equals(parent.getIdz().toString());
    }


}
