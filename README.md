# Mvvm Android

Using android dataBinding step by step

Handle TabLayout with dataBinding and also recyclerView with different type show

for the first step you need add this code into your module gradle 

```groovy
    dataBinding {
        enabled = true
    }
```
- Binding activity 
   - change [activity_main.xml](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/res/layout/activity_main.xml)
        ```xml
          <layout xmlns:android="http://schemas.android.com/apk/res/android">
              <data>
                  <!--UserInfo is BaseObservable Object-->
                  <variable name="temp"        type="com.prime.model.UserInfo"/>
                  <!--MyHandler is method in MainActivity -->
                  <variable name="handler"     type="com.prime.android.MainActivity.MyHandler"/>
              </data>
              <LinearLayout
                  android:orientation="vertical"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent">
                  
                  <!--you can bind variable like this @={temp.nicename}-->  
                  <TextView
                      android:layout_width="match_parent"
                      android:layout_height="42dp"
                      android:text="@={temp.nicename}" />

                  <!--you can add method as action in this way @{() -> handler.callService()}-->
                  <Button
                      android:layout_width="match_parent"
                      android:layout_height="42dp"
                      android:text="@={temp.nicename}"
                      android:onClick="@{() -> handler.callService()}"/>
              </LinearLayout>
          </layout>
        ```
   - change [MainActivity](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/java/com/prime/android/MainActivity.java)
  
        change
        ```java 
           setContentView(R.layout.activity_main);             
        ```          
        to 
        ```java
            ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);  
        ```    
        and after that you can bind your parameters
        ```java
            binding.setTemp(user);
            binding.setHandler(new MyHandler());
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



