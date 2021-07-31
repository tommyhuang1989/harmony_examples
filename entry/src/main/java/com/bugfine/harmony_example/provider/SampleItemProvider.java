package com.bugfine.harmony_example.provider;

import com.bugfine.harmony_example.ResourceTable;
import com.bugfine.harmony_example.domain.SampleItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class SampleItemProvider extends BaseItemProvider {
    private List<SampleItem> list; //用来存放每一项的逻辑数据
    private AbilitySlice slice; //关联的界面

    public SampleItemProvider(List<SampleItem> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;
    }

    @Override //返回填充的表项个数
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override //根据position返回对应的数据
    public Object getItem(int i) {
        if (list != null && i >=0 && i < list.size()) {
            return  list.get(i);
        }
        return null;
    }

    @Override //返回某一项的id。
    public long getItemId(int i) {
        return i;
    }

    @Override //根据position返回对应的界面组件。 其实就是为 item 填充数据，并放回控件显示在界面上
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        // 已经是跟逻辑有很密切的关系了，甚至关系到界面控件
        final Component cpt;

        if (component == null) {
            //读取 item 的样式（xml 文件）
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_sample, null, false);
        }
        else {
            cpt = component;
        }

        //进行数据填充
        SampleItem sample_item = list.get(i);
        Text text =(Text)cpt.findComponentById(ResourceTable.Id_item_index);
        text.setText(sample_item.getName());
        return cpt;
    }
}
