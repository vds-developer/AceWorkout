package vds.developer.aceworkout.helper

import androidx.lifecycle.LiveData

class MutableListLiveData<T> : LiveData<T> () {
//    public void addAll(List<T> items) {
//        if (getValue() != null && items != null) {
//            getValue().addAll(items);
//            setValue(getValue());
//        }
//    }
//
//    public void clear() {
//        if (getValue() != null) {
//            getValue().clear();
//            setValue(getValue());
//        }
//    }
//
//    @Override public void setValue(List<T> value) {
//        super.setValue(value);
//    }
//
//    @Nullable @Override public List<T> getValue() {
//        return super.getValue();
//    }
//}
//
//// add changed listener
//mMessageList.observe(mActivity, new Observer() {
//    @Override public void onChanged(@Nullable Object o) {
//        notifyDataSetChanged();
//    }
//});
}