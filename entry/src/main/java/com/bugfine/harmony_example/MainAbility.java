package com.bugfine.harmony_example;

import com.bugfine.harmony_example.slice.ListAbilitySlice;
import com.bugfine.harmony_example.slice.MainAbilitySlice;
import com.bugfine.harmony_example.slice.SettingAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
//        super.setMainRoute(MainAbilitySlice.class.getName());
//        super.setMainRoute(ListAbilitySlice.class.getName());
        super.setMainRoute(SettingAbilitySlice.class.getName());//20210801, tommy, add setting demo
    }
}
