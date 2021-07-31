package com.bugfine.harmony_example.slice;

import com.bugfine.harmony_example.ResourceTable;
import com.bugfine.harmony_example.domain.SampleItem;
import com.bugfine.harmony_example.provider.SampleItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class ListAbilitySlice extends AbilitySlice {
    private Button btn_add;
    private List<SampleItem> list;
    private SampleItemProvider provider;
    private ListContainer list_container;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_list_demo);
        init_list();

        btn_add = (Button) findComponentById(ResourceTable.Id_btn_add);
        btn_add.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                //初始化之后修改数据
                list.add(new SampleItem("Item" + provider.getCount()));
                list.add(new SampleItem("tommy.huang"));
                list_container.setBindStateChangedListener(new Component.BindStateChangedListener() {
                    @Override
                    public void onComponentBoundToWindow(Component component) {
                        provider.notifyDataChanged();
                    }

                    @Override
                    public void onComponentUnboundFromWindow(Component component) {

                    }
                });
            }
        });
    }

    //初始化数据和绑定基本事件
    private void init_list() {
        list_container = (ListContainer) findComponentById(ResourceTable.Id_list_mission);//通过 id 找到 listContainer
        list = get_data(); //获取数据
        provider = new SampleItemProvider(list, this);//新建 ItemProvider，并准备好数据
        list_container.setItemProvider(provider);//为 listContainer 绑定数据。

        //set click listener
        list_container.setItemClickedListener((container, component, position, id) -> {
            SampleItem item = (SampleItem)list_container.getItemProvider().getItem(position);
            new ToastDialog(this).setText("you clicked: " + item.getName()).setAlignment(LayoutAlignment.CENTER).show();
        });

        //set long click listener
        list_container.setItemLongClickedListener((container, component, position, id) -> {
            SampleItem item = (SampleItem)list_container.getItemProvider().getItem(position);
            new ToastDialog(this).setText("you long clicked: " + item.getName()).setAlignment(LayoutAlignment.CENTER).show();
            return false;
        });
    }

    //简单的数据测试，真实环境应该要连接数据库
    private List<SampleItem> get_data() {
        ArrayList<SampleItem> list = new ArrayList<SampleItem>();
        for (int i = 0; i <= 8; i++) {
            list.add(new SampleItem("Item" + i));
        }
        return list;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
