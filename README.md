# Mvvm Android

Using android dataBinding step by step

Handle TabLayout with dataBinding and also recyclerView with different type show

for the first step you need add this code into your module gradle 

```groovy
    dataBinding {
        enabled = true
    }
```

- Binding Adapter to TabLayout and ViewPager
    - you can check this layout for that [activity_page_list.xml](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/src/main/res/layout/activity_page_list.xml)
    - for Binding Parameter you can check [PageListActivity.java](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/java/com/prime/android/PageListActivity.java) to realise that better.
    
- Binding ImageView source

    for binding image src we need to use @BindingAdapter("android:src") and you can check it into [BaseActivity](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/java/com/prime/baseClass/BaseActivity.java)

    ```java
        @BindingAdapter("android:src")
        public static void BindingImageDetails(ImageViewCustom imageView, String url) {
            if (!PublicFunction.StringIsEmptyOrNull(url)) {
                imageView.loadImage(url);
            }
        }
    ```



