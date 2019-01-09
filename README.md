# Mvvm Android

Using android dataBinding step by step

Handle TabLayout with dataBinding and also recyclerView with different type show

for the first step you need add this code into your module gradle 

```groovy
    dataBinding {
        enabled = true
    }
```

Binding activity 
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

Binding Adapter to TabLayout and ViewPager
    - [activity_page_list.xml](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/src/main/res/layout/activity_page_list.xml)
    ```xml
    <layout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>
            <!-- variable of ViewPager for tabLayout-->
            <variable
                name="tabHandler"
                type="com.prime.custom_view.CustomViewPager" />

            <!-- adapter for ViewPager -->
            <variable
                name="adapterHandler"
                type="com.prime.android.PageListActivity.pagerAdapter" />

        </data>

        <androidx.coordinatorlayout.widget.CoordinatorLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <include layout="@layout/layout_toolbar"/>

            <LinearLayout
                android:orientation="vertical"
                app:layout_behavior="@string/appbar_scrolling_view_behavior"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                <!--assign viewPager to Tablayout app:pager="@{tabHandler}"-->
                <!--app:pager isn't available for assign ViewPager view, so I added that into java code with @BindingAdapter({"pager"})-->
                <com.prime.custom_view.TabLayoutCustom
                    android:id="@+id/tab_layout"
                    style="@style/CustomTabLayoutStyle"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@color/colorPrimary"
                    app:pager="@{tabHandler}" />

                <!--assign adapter to viewPager android:pagerAdapter="@{adapterHandler}"-->
                <!--android:pagerAdapter isn't available too and I added that into java code with @BindingAdapter({"android:pagerAdapter"})-->
                <com.prime.custom_view.CustomViewPager
                    android:id="@+id/pager"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:pagerAdapter="@{adapterHandler}" />

            </LinearLayout>

        </androidx.coordinatorlayout.widget.CoordinatorLayout>
    </layout>
    ```


   I implemented @BindingAdapter into [BaseActivity](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/java/com/prime/baseClass/BaseActivity.java) you can check theme here.


    - for Binding Parameter you can check [PageListActivity.java](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/java/com/prime/android/PageListActivity.java) to realise that better.


Binding ImageView source

    for binding image src we need to use @BindingAdapter("android:src") and you can check it into [BaseActivity](https://github.com/alishatergholi/Android-DataBinding/blob/master/app/src/main/java/com/prime/baseClass/BaseActivity.java)

    ```java
        @BindingAdapter("android:src")
        public static void BindingImageDetails(ImageViewCustom imageView, String url) {
            if (!PublicFunction.StringIsEmptyOrNull(url)) {
                imageView.loadImage(url);
            }
        }
    ```



