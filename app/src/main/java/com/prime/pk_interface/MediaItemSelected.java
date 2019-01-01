package com.prime.pk_interface;

import com.prime.enum_package.RowType;
import com.prime.model.ItemsDataParcelable;

public interface MediaItemSelected {
    void onSelectedItem(ItemsDataParcelable item, RowType type);
}
