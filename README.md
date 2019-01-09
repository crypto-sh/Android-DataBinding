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
        and after that you can bind your parametr
        ```java
            binding.setTemp(user);
            binding.setHandler(new MyHandler());
        ```   



