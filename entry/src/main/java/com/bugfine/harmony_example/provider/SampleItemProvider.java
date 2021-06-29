package com.bugfine.harmony_example.provider;

import com.bugfine.harmony_example.ResourceTable;
import com.bugfine.harmony_example.domain.SampleItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class SampleItemProvider extends BaseItemProvider {
    private List<SampleItem> list;
    private AbilitySlice slice;

    public SampleItemProvider(List<SampleItem> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        if (list != null && i >=0 && i < list.size()) {
            return  list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        // 已经是跟逻辑有很密切的关系了，甚至关系到界面控件
        final Component cpt;

        if (component == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_sample, null, false);
        }
        else {
            cpt = component;
        }

        SampleItem sample_item = list.get(i);
        Text text =(Text)cpt.findComponentById(ResourceTable.Id_item_index);
        text.setText(sample_item.getName());
        return cpt;
    }
}
