package com.ynnjii.utils;

import com.ynnjii.base.PageResult;
import com.ynnjii.base.Tree;
import com.ynnjii.sys.domain.SysDepartment;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dto.DeptWithUser;
import com.ynnjii.sys.dto.MenuTree;
import com.github.pagehelper.Page;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目名称：车到山前后台
 * 类描述：容器转换
 * 创建人：yzh
 * 创建时间：2017/6/2
 * 备注：
 */
public class ContainerUtils {

    /**
     * 转换 分页
     * @param page
     * @return
     */
    public static PageResult changeToPage(Page page) {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(page);
        return pageResult;
    }

    /**
     * 转换 树
     * @param list
     * @return
     */
    public static List menuChangeToTree(List<SysMenu> list) {
        return menuChangeToTree(list,null);
    }

    /**
     * 转换 树
     * @param list
     * @param menuIds exist
     * @return
     */
    public static List menuChangeToTree(List<SysMenu> list,List<Integer> menuIds) {
        if (list == null || list.size() == 0) {
            return new ArrayList();
        }

        List<Tree> menuList = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            Tree tree = new Tree();
            tree.setId(sysMenu.getId().toString());
            if (sysMenu.getParentId() == 0) {
                tree.setParent("#");
            } else {
                tree.setParent(sysMenu.getParentId().toString());
            }
            tree.setText(sysMenu.getName());

            if (!StringUtils.isNullOrEmpty(menuIds)) {
                if (menuIds.contains(sysMenu.getId())) {
                    tree.setSelected(true);
                }
            }
            menuList.add(tree);
        }
        return menuList;
    }

    /**
     * 转换 树
     * @param list
     * @return
     */
    public static List departmentChangeToTree(List<SysDepartment> list) {
        return departmentChangeToTree(list,null);
    }

    public static List departmentChangeToTree(List<SysDepartment> list,List<Integer> departmentIds) {
        if (list == null || list.size() == 0) {
            return new ArrayList();
        }

        List<Tree> menuList = new ArrayList<>();
        for (SysDepartment sysMenu : list) {
            Tree tree = new Tree();
            tree.setId(sysMenu.getId().toString());
            if (sysMenu.getParentId() == 0) {
                tree.setParent("#");
            } else {
                tree.setParent(sysMenu.getParentId().toString());
            }
            tree.setText(sysMenu.getName());

            if (!StringUtils.isNullOrEmpty(departmentIds)) {
                if (departmentIds.contains(sysMenu.getId())) {
                    tree.setSelected(true);
                }
            }
            menuList.add(tree);
        }
        return menuList;
    }

    public static List userChangeToTree(List<DeptWithUser> list) {
        return userChangeToTree(list,null);
    }

    public static List userChangeToTree(List<DeptWithUser> list,List<Integer> userIds) {
        if (list == null || list.size() == 0) {
            return new ArrayList();
        }

        List<Tree> menuList = new ArrayList<>();
        for (DeptWithUser deptWithUser : list) {
            Tree tree = new Tree();
            tree.setId("d" + deptWithUser.getId());
            if (deptWithUser.getParentId() == 0) {
                tree.setParent("#");
            } else {
                tree.setParent("d" + deptWithUser.getParentId());
            }
            tree.setText(deptWithUser.getName());
            menuList.add(tree);

            if (!StringUtils.isNullOrEmpty(deptWithUser.getUsers())) {
                for (SysUser sysUser : deptWithUser.getUsers()) {
                    Tree tree2 = new Tree();
                    tree2.setId(sysUser.getId().toString());
                    tree2.setParent("d" + deptWithUser.getId());
                    tree2.setText(sysUser.getName());
                    if (!StringUtils.isNullOrEmpty(userIds)) {
                        if (userIds.contains(sysUser.getId())) {
                            tree2.setSelected(true);
                        }
                    }
                    menuList.add(tree2);
                }
            }
        }
        return menuList;
    }

    /**
     * 转换  左侧菜单
     * @param list
     * @return
     */
    public static List changeToMenuTree(List<SysMenu> list) {
        if (list == null || list.size() == 0) {
            return new ArrayList();
        }

        List<SysMenu> menuList = list.stream().filter(x -> x.getParentId() == 0).collect(Collectors.toList());
        if (menuList == null || menuList.size() == 0) {
            return new ArrayList();
        }

        List<MenuTree> result = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            MenuTree menuTree = new MenuTree();
            changeTo(sysMenu,menuTree);
            result.add(menuTree);
        }

        for (MenuTree menuTree : result) {
            List children = list.stream().filter(x -> x.getParentId() == menuTree.getId().intValue()).collect(Collectors.toList());
            children.sort(Comparator.comparing(SysMenu::getSort));
            menuTree.setChildren(children);
        }

        result.sort(Comparator.comparing(MenuTree::getSort));

        return result;
    }

    /**
     * 转换类型
     * @param f from
     * @param t to
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T,E> E changeTo(T f,E t) {
        try {
            PropertyUtils.copyProperties(t,f);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
        return t;
    }

}
