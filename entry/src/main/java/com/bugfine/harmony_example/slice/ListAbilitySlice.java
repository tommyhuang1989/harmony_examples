package com.bugfine.harmony_example.slice;

import com.bugfine.harmony_example.ResourceTable;
import com.bugfine.harmony_example.domain.SampleItem;
import com.bugfine.harmony_example.provider.SampleItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class ListAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_list_demo);
        init_list();
    }

    private void init_list() {
        ListContainer list_container = (ListContainer) findComponentById(ResourceTable.Id_list_mission);
        List<SampleItem> list = get_data();
        SampleItemProvider provider = new SampleItemProvider(list, this);
        list_container.setItemProvider(provider);

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
