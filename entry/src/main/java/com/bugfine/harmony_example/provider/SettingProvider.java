package com.bugfine.harmony_example.provider;

import com.bugfine.harmony_example.ResourceTable;
import com.bugfine.harmony_example.domain.SettingItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;
import java.util.List;

public class SettingProvider extends BaseItemProvider{
    // ListContainer的数据集合
    private List<SettingItem> settingList;
    private AbilitySlice slice;

    public SettingProvider(List<SettingItem> list, AbilitySlice slice) {
        this.settingList = list;
        this.slice = slice;
    }
    // 用于保存列表项中的子组件信息
    public class SettingHolder {
        Image settingIma;
        Text settingText;
        Switch settingSwitch;

        SettingHolder(Component component) {
            settingIma = (Image) component.findComponentById(ResourceTable.Id_image_setting);
            settingText = (Text) component.findComponentById(ResourceTable.Id_text_setting);
            settingSwitch = (Switch) component.findComponentById(ResourceTable.Id_switch_setting);

            settingSwitch.setTrackElement(trackElementInit(
                    new ShapeElement(slice, ResourceTable.Graphic_Graphic_track_on_element),
                    new ShapeElement(slice, ResourceTable.Graphic_track_off_element)));

            settingSwitch.setThumbElement(thumbElementInit(
                    new ShapeElement(slice, ResourceTable.Graphic_thumb_on_element),
                    new ShapeElement(slice, ResourceTable.Graphic_thumb_off_element)));

        }

        private StateElement trackElementInit(ShapeElement on, ShapeElement off) {
            StateElement trackElement = new StateElement();
            trackElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, on);
            trackElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, off);
            return trackElement;
        }



        private StateElement thumbElementInit(ShapeElement on, ShapeElement off) {
            StateElement thumbElement = new StateElement();
            thumbElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, on);
            thumbElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, off);
            return thumbElement;
        }
    }

    @Override
    public int getCount() {
        return settingList == null ? 0 : settingList.size();
    }

    @Override
    public Object getItem(int position) {
        if (settingList != null && position >= 0 && position < settingList.size()){
            return settingList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component component, ComponentContainer componentContainer) {
        final Component cpt;
        SettingHolder holder;

        if (component == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_layout_item_setting, null, false);
            holder = new SettingHolder(cpt);
            // 将获取到的子组件信息绑定到列表项的实例中
            cpt.setTag(holder);
        } else {
            cpt = component;
            // 从缓存中获取到列表项实例后，直接使用绑定的子组件信息进行数据填充。
            holder = (SettingHolder) cpt.getTag();
        }

        SettingItem setting = settingList.get(position);
        holder.settingIma.setPixelMap(setting.getImageId());
        holder.settingText.setText(setting.getSettingName());
        holder.settingSwitch.setChecked(setting.isChecked());
        return cpt;
    }
}