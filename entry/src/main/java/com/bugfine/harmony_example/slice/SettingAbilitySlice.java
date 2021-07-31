package com.bugfine.harmony_example.slice;

import com.bugfine.harmony_example.ResourceTable;
import com.bugfine.harmony_example.domain.SettingItem;
import com.bugfine.harmony_example.provider.SettingProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class SettingAbilitySlice extends AbilitySlice {
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_setting_slice);
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        SettingProvider provider = new SettingProvider(getData(), this);
        listContainer.setItemProvider(provider);
    }

    private List<SettingItem> getData() {
        ArrayList<SettingItem> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new SettingItem(
                    ResourceTable.Media_icon,
                    "SettingName" + i,
                    i % 3 == 0
            ));
        }
        return data;
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
