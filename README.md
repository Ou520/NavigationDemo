

# Android Jetpack 的 Navigation导航组件的使用简介

### 介绍

Android Jetpack 的 Navigation(导航组件) 可帮助您实现导航，无论是简单的按钮点击，还是应用栏和抽屉式导航栏等更为复杂的模式，该组件均可应对。导航组件还通过遵循一套既定原则来确保一致且可预测的用户体验。

**导航组件由以下三个关键部分组成：**

- **导航图：** 在一个集中位置包含所有导航相关信息的 XML 资源。这包括应用内所有单个内容区域（称为目标）以及用户可以通过应用获取的可能路径。

- **NavHost：** 显示导航图中目标的空白容器。导航组件包含一个默认 NavHost 实现 (NavHostFragment)，可显示 Fragment 目标

- **NavController：** 在 NavHost 中管理应用导航的对象。当用户在整个应用中移动时，NavController 会安排 NavHost 中目标内容的交换


**Navigation(导航组件)优势，包括以下内容：**

- **处理 Fragment 事务**

- **默认情况下，正确处理往返操作**

- **为动画和转换提供标准化资源**

- **实现和处理深层链接**

- **包括导航界面模式（例如抽屉式导航栏和底部导航），用户只需完成极少的额外工作**

- **Safe Args - 可在目标之间导航和传递数据时提供类型安全的 Gradle 插件**

- **ViewModel 支持 - 您可以将 ViewModel 的范围限定为导航图，以在图表的目标之间共享与界面相关的数据**

.

# 开始使用

### 引入Navigation导航组件的依赖

```java
dependencies {
	...

    //Navigation库的依赖
	def nav_version = "2.0.0"
    implementation "androidx.navigation:navigation-fragment:$nav_version"
	implementation "androidx.navigation:navigation-ui:$nav_version"
	
	//页面间通过ViewModel传递数据时使用
    def lifecycle_version = "2.2.0"
    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime:$lifecycle_version"
}
```

> **注意：** Navigation 组件需要 Android Studio 3.3 或更高版本，并且依赖于 Java 8 语言功能。

.

# Navigation导航组件的简单使用

**使用步骤如下**

**1.创建Fragment**

**创建第一个Fragment**

```java
public class HomeFragment extends Fragment {

    private Button btn;

    //构造方法
    public HomeFragment() { }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initListener();
    }

    private void initView() {

        btn = getView().findViewById(R.id.btn_main);

    }

    private void initListener() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //跳转页面方式一
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_homeFragment_to_oneFragment);

                }

            }
        });

    }

}
```

**创建第二个Fragment**


```java
public class OneFragment extends Fragment {

    public OneFragment() { }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {

    //按钮的监听
    getView().findViewById(R.id.btn_one)
            //跳转页面方式二
            .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_oneFragment_to_homeFragment));
    }
}
```

**2.创建导航图**

> **向项目添加导航图，请执行以下操作：**
> 1. 在“Project”窗口中，右键点击 res 目录，然后依次选择 **New > Android Resource File**。此时系统会显示 **New Resource File** 对话框。
>
> 2. 在 **File name** 字段中输入名称，例如“nav_graph”
>
> 3. 从 **Resource type** 下拉列表中选择 Navigation，然后点击 OK

添加图表后，Android Studio 会在 Navigation Editor 中打开该图表。在 Navigation Editor 中，您可以直观地修改导航图，或直接修改底层 XML

![](https://img-blog.csdnimg.cn/img_convert/98d9e59c4c6c91844939f0e858966202.png)

**1. Destinations panel：** 列出了导航宿主和目前位于 Graph Editor 中的所有目的地。

**2. Graph Editor：** 包含导航图的视觉表示形式。您可以在 Design 视图和 Text 视图中的底层 XML 表示形式之间切换。

**3. Attributes：** 显示导航图中当前所选项的属性。



点击 Text 标签页可查看相应的 `nav.xml`，它应类似于以下代码段：
	
```java
<?xml version="1.0" encoding="utf-8"?>
<!--  设置那个Fragment是主页： app:startDestination="@id/homeFragment" -->
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.ouwenbin.navigationdemo.fragment.HomeFragment"
        android:label="Home"
        tools:layout="@layout/fragment_home">

        <!--
        //设置页面的动画效果
        app:enterAnim="@anim/nav_default_enter_anim"
        app:exitAnim="@anim/nav_default_exit_anim"

        //设置Fragment位置在栈顶
        app:popUpToInclusive="false"
        -->
        <action
            android:id="@+id/action_homeFragment_to_oneFragment"
            app:destination="@id/oneFragment"
            app:enterAnim="@anim/slide_from_left"
            app:exitAnim="@anim/slide_to_right"
            app:popUpToInclusive="false" />
        <action
            android:id="@+id/action_homeFragment_to_twoFragment"
            app:destination="@id/twoFragment"
            app:enterAnim="@anim/slide_from_left"
            app:exitAnim="@anim/slide_to_right" />
    </fragment>
    <fragment
        android:id="@+id/oneFragment"
        android:name="com.ouwenbin.navigationdemo.fragment.OneFragment"
        android:label="One"
        tools:layout="@layout/fragment_one">
        <action
            android:id="@+id/action_oneFragment_to_homeFragment"
            app:destination="@id/homeFragment"
            app:enterAnim="@anim/slide_from_left"
            app:exitAnim="@anim/slide_to_right"
            app:popUpTo="@+id/homeFragment"
            app:popUpToInclusive="true" />
    </fragment>
</navigation>
```

**3. 向 Activity 添加 NavHost(容器)**

导航宿主是 Navigation 组件的核心部分之一。导航宿主是一个空容器，用户在您的应用中导航时，目的地会在该容器中交换进出。

**在MainActivity的XML中添加 `NavHostFragment`**

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- 添加NavHostFragment（Fragment的容器）  -->
    <fragment
        android:id="@+id/fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="409dp"
        android:layout_height="729dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/nav" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

**在MainActivity的代码**

```java
public class MainActivity extends AppCompatActivity {

    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = Navigation.findNavController(this,R.id.fragment);
        //添加标题栏(在标题栏显示返回按钮)
        NavigationUI.setupActionBarWithNavController(this,controller);

    }

    @Override
    public boolean onSupportNavigateUp() {

        return controller.navigateUp();
    }
}
```


> 到这里Navigation导航组件的简单使用就结束了,下面介绍Navigation组件的其他功能即用法。


.

.

### 为页面跳转添加动画

**1. 创建页面的进入动画** `slide_from_left.xml`

```java
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
 <!-- 平移 -->
<translate
    android:fromXDelta="-100%"
    android:toXDelta="0%"
    android:duration = "500">
</translate>
</set>
```

**2. 创建页面的退出动画** `slide_to_right.xml`

```java
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!-- 平移 -->
<translate
android:fromXDelta="0%"
android:toXDelta="100%"
android:duration = "500">
</translate>
</set>
```

**3. 在** `nav.xml` **文件中使用定义好的动画**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201223163230440.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjMyNDk3OQ==,size_16,color_FFFFFF,t_70#pic_center)

```java
<fragment
    android:id="@+id/homeFragment"
    android:name="com.ouwenbin.navigationdemo.fragment.HomeFragment"
    android:label="Home"
    tools:layout="@layout/fragment_home">

    <!--
    //设置页面的动画效果
    app:enterAnim="@anim/nav_default_enter_anim"
    app:exitAnim="@anim/nav_default_exit_anim"

    //设置Fragment位置在栈顶
    app:popUpToInclusive="false"
    -->
    <action
        android:id="@+id/action_homeFragment_to_oneFragment"
        app:destination="@id/oneFragment"
        app:enterAnim="@anim/slide_from_left"
        app:exitAnim="@anim/slide_to_right"
        app:popUpToInclusive="false" />
    <action
        android:id="@+id/action_homeFragment_to_twoFragment"
        app:destination="@id/twoFragment"
        app:enterAnim="@anim/slide_from_left"
        app:exitAnim="@anim/slide_to_right" />
</fragment>
```
> **注：**
>    记得设置Fragment位置在栈顶 `app:popUpToInclusive="false"` ，否则当按下系统的返回按钮时无法退出应用
    

.

.

### Navigation导航组件在在目的地之间传递数据

Navigation组件在页面间传递数据大致分为两种：

- [**传递静态数据**](https://developer.android.google.cn/guide/navigation/navigation-pass-data)

- **传递动态数据数据**

这里以传递动态数据为例进行讲解
> 因为这种传递数据的方式可以满足日常开发的绝大多数使用情况
>
> 可以使用 `Bundle` 或者 `ViewModel` 来实现数据的传递，推荐使用`ViewModel`

**1. 使用 `Bundle` 来实现数据的传递**


**数据传递方(HomeFragment)**

```java
public class HomeFragment extends Fragment {

    private Button btn;

    //构造方法
    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initListener();
    }

    private void initView() {

    	btn = getView().findViewById(R.id.btn_main);
    }

    private void initListener() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

   					String text = "我是要传递给OneFragment的数据"

                    //使用Bundle包装要传递的数据
                    Bundle bundle = new Bundle();
                    bundle.putString("name_key",text);

                    //跳转页面并传递数据(bundle)
                    NavController controller = Navigation.findNavController(view);
                    controller.navigate(R.id.action_homeFragment_to_oneFragment,bundle);
                }
            }
        });

    }
}
```

**数据接收方(OneFragment)**

```java
public class OneFragment extends Fragment {

    public OneFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {

		//接收HomeFragment传递过来的数据
        String HomeToData =  getArguments().getString("name_key");
		
		Toast.makeText(getActivity(), "接收的内容:"+HomeToData, Toast.LENGTH_SHORT).show();


        //按钮
        getView().findViewById(R.id.btn_one)
                //跳转页面
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_oneFragment_to_homeFragment));

    }

}
```


**2. 使用 `ViewModel` 来实现数据的传递**

**创建ViewModel类**
> **注：** 要记得先添加相关的依赖

```java
public class MyViewModel extends ViewModel {

    private MutableLiveData<String> text;

    public MyViewModel(){ }

    public MutableLiveData<String> getText(){

        //初始化text
        if (text == null){
            text = new MutableLiveData<>();
            text.setValue("OneFragment");
        }
        return text;
    }

    public void add(String s) {

        getText().setValue(s);
    }
}
```

**数据传递方(HomeFragment)**

```java
public class HomeFragment extends Fragment {

    private Button btn;
	private MyViewModel model;

    //构造方法
    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initListener();
    }

    private void initView() {

    	btn = getView().findViewById(R.id.btn_main);
    }

    private void initListener() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

					String text = "我是要传递给OneFragment的数据"

                    //使用ViewModel来实现数据的传递
                    model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
                    model.add(text);//添加数据

                    //跳转页面并传递数据(bundle)
                    NavController controller = Navigation.findNavController(view);
                    controller.navigate(R.id.action_homeFragment_to_oneFragment);
                }

            }
        });

    }
}
```

**数据接收方(OneFragment)**

```java
public class OneFragment extends Fragment {

	private MyViewModel model;

    public OneFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {

		//接收HomeFragment传递过来的数据
        //初始化ViewMode
		model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

		String text =  model.getText().getValue();
		
	    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

        //按钮
        getView().findViewById(R.id.btn_one)
                //跳转页面
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_oneFragment_to_homeFragment));

    }

}
```

.

.

### [使用 NavigationUI 更新界面组件](https://developer.android.google.cn/guide/navigation/navigation-ui)

Navigation 组件包含 NavigationUI 类。此类包含使用顶部应用栏、抽屉式导航栏和底部导航栏管理导航的静态方法。

.

### BottomNavigationView(底部导航栏)的使用介绍

**效果演示：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224103636270.gif#pic_center)

**使用步骤：**

**1. 创建Fragment页面**

创建4个Fragment页面，分别是 `FirstFragment`，`SecondFragment`, `ThirdFragment`, `FourthFragment`；这里以 `FirstFragment` 为例 ，因为代码都是一样的这里就不贴出来了

**XML文件部分：**
	
```java
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".NavigationUI.BottomNavigationView.fragment.FirstFragment">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/textView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="168dp"
            android:layout_marginTop="365dp"
            android:layout_marginEnd="185dp"
            android:layout_marginBottom="347dp"
            android:text="1"
            android:textSize="40dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</FrameLayout>
```

**Java代码部分：**

```java
public class FirstFragment extends Fragment {
    public FirstFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

}
```


**2. 创建导航图**

> 因为这些页面之间是通过 `BottomNavigationView` 来实现导航的，使用在创建导航图的时候不需要为页面添加跳转的逻辑。

**视图部分：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224095149640.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjMyNDk3OQ==,size_16,color_FFFFFF,t_70#pic_center)

`bottom_navigation.xml `**文件部分：**

```java
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/buttom_navigation"
    app:startDestination="@id/firstFragment">
    <fragment
        android:id="@+id/firstFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.FirstFragment"
        android:label="fragment_first"
        tools:layout="@layout/fragment_first" />
    <fragment
        android:id="@+id/secondFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.SecondFragment"
        android:label="fragment_second"
        tools:layout="@layout/fragment_second" />
    <fragment
        android:id="@+id/thirdFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.ThirdFragment"
        android:label="fragment_third"
        tools:layout="@layout/fragment_third" />
    <fragment
        android:id="@+id/fourthFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.FourthFragment"
        android:label="fragment_fourth"
        tools:layout="@layout/fragment_fourth" />
</navigation>
```

> **注：**
> 
> 这里 `fragment` 的 `id` 必须要和 下面Menu(菜单)中的 `item` 的 `id` 相互对应，并且相同；否则就会导致 `BottomNavigationView` 的导航失败。


**3. 创建Menu(菜单)**

因为 `BottomNavigationView` 里的每一个Item 是通过加载一Menu(菜单)来实现的，因此我们需要创建一个Menu(菜单)来定义每一个Item。

	
```java
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
	<!-- 这里的图标和标题请按照需求自行添加 -->
    <item
    android:id="@+id/firstFragment"
    android:icon="@drawable/ic_looks_one_black_24dp"
    android:title="First"></item>

    <item
        android:id="@+id/secondFragment"
        android:icon="@drawable/ic_looks_two_black_24dp"
        android:title="Second"></item>

    <item
        android:id="@+id/thirdFragment"
        android:icon="@drawable/ic_looks_3_black_24dp"
        android:title="Third"></item>
    <item
        android:id="@+id/fourthFragment"
        android:icon="@drawable/ic_looks_4_black_24dp"
        android:title="Fourth"></item>
</menu>
```

> **注：**
> 这里Menu里的 `item` 的 `id` 必须要和 上面的导航图中的 `fragment` 的 `id` 相互对应，并且相同；否则就会导致 `BottomNavigationView` 的导航失败 ；并且item的数量不超过5个。


**4. 在Activity的XML文件中引入 `BottomNavigationView` 和 添加 navHostFragment(容器)**

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"    tools:context=".NavigationUI.BottomNavigationView.BottomNavigationActivity">
<!--
    app:menu: 设置导航的item
    app:itemBackground: 设置item的背景颜色
    app:elevation: 设置隐藏文本，选中后显示文本
    app:itemIconTint：设置图标的颜色
    app:itemIconSize: 设置图标的大小
    app:itemTextColor：设置文字的颜色
    app:labelVisibilityMode: 设置文本的显示效果

    方法和属性介绍：https://developer.android.google.cn/reference/com/google/android/material/bottomnavigation/BottomNavigationView?hl=en#setItemRippleColor(android.content.res.ColorStateList)
	-->
<com.google.android.material.bottomnavigation.BottomNavigationView
    android:id="@+id/bottomNavigationView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    android:background="@color/colorAccent"
    app:itemIconTint="@color/color_state_menu_navi"
    app:itemTextColor="@color/color_state_menu_navi"
    app:labelVisibilityMode="labeled"
    app:menu="@menu/menu" />

<fragment
    android:id="@+id/fragment2"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="409dp"
    android:layout_height="673dp"
    app:defaultNavHost="true"
    app:layout_constraintBottom_toTopOf="@+id/bottomNavigationView"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:navGraph="@navigation/bottom_navigation" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**5. 在您的主 Activity 类中，通过主 Activity 的 onCreate() 方法调用 setupWithNavController()**

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_navigation);
    
	//初始化
    BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationView);
    //创建导航控制器
    NavController controller = Navigation.findNavController(this,R.id.fragment2);
    //创建标题栏配置的对象
    AppBarConfiguration configuration = new  AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
    //添加标题栏
    NavigationUI.setupActionBarWithNavController(this,controller,configuration);
    //使底部导航栏与导航控制器建立绑定关系
    NavigationUI.setupWithNavController(bottomNavigationView,controller);
}
```

.

.

### BottomNavigationView + ViewPager 的使用介绍

**效果演示：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020122410592882.gif#pic_center)


**使用步骤：**

**1. 创建Fragment页面**

创建4个Fragment页面，分别是 `OneFragment`，`TwoFragment`, `ThreeFragment`, `FourFragment`；这里以 `OneFragment` 为例 ，因为代码都是一样的这里就不贴出来了

**XML文件部分：**
	
```java
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".NavigationUI.BottomNavigationView.fragment.FirstFragment">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/textView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="168dp"
            android:layout_marginTop="365dp"
            android:layout_marginEnd="185dp"
            android:layout_marginBottom="347dp"
            android:text="1"
            android:textSize="40dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</FrameLayout>
```

**Java代码部分：**

```java
public class OneFragment extends Fragment {
    public FirstFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one2, container, false);
    }
}
```


**2. 创建导航图**

因为这些页面之间是通过 `BottomNavigationView` 来实现导航的，使用在创建导航图的时候不需要为页面添加跳转的逻辑。

**视图部分：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224104739450.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjMyNDk3OQ==,size_16,color_FFFFFF,t_70#pic_center)

`view_pager.xml`**文件部分：**

```java
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/buttom_navigation"
    app:startDestination="@id/firstFragment">
    <fragment
        android:id="@+id/firstFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.FirstFragment"
        android:label="fragment_first"
        tools:layout="@layout/fragment_first" />
    <fragment
        android:id="@+id/secondFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.SecondFragment"
        android:label="fragment_second"
        tools:layout="@layout/fragment_second" />
    <fragment
        android:id="@+id/thirdFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.ThirdFragment"
        android:label="fragment_third"
        tools:layout="@layout/fragment_third" />
    <fragment
        android:id="@+id/fourthFragment"
        android:name="com.ouwenbin.navigationdemo.NavigationUI.BottomNavigationView.fragment.FourthFragment"
        android:label="fragment_fourth"
        tools:layout="@layout/fragment_fourth" />
</navigation>
```

> **注：**
> 
> 这里 `fragment` 的 `id` 必须要和 下面Menu(菜单)中的 `item` 的 `id` 相互对应，并且相同；否则就会导致 `BottomNavigationView` 的导航失败。


**3. 创建Menu(菜单)**

因为 `BottomNavigationView` 里的每一个Item 是通过加载一Menu(菜单)来实现的，因此我们需要创建一个Menu(菜单)来定义每一个Item。

	
```java
<menu xmlns:android="http://schemas.android.com/apk/res/android">
	<!-- 这里的图标和标题请按照需求自行添加 -->
    <item
        android:id="@+id/oneFragment2"
        android:orderInCategory="0"
        android:icon="@drawable/ic_looks_one_black_24dp"
        android:title="First" />

    <item
        android:id="@+id/twoFragment2"
        android:orderInCategory="1"
        android:icon="@drawable/ic_looks_two_black_24dp"
        android:title="Second" />

    <item
        android:id="@+id/threeFragment2"
        android:orderInCategory="2"
        android:icon="@drawable/ic_looks_3_black_24dp"
        android:title="Third" />
    <item
        android:id="@+id/fourFragment2"
        android:orderInCategory="3"
        android:icon="@drawable/ic_looks_4_black_24dp"
        android:title="Fourth" />
</menu>
```

> **注：**
> 这里Menu里的 `item` 的 `id` 必须要和 上面的导航图中的 `fragment` 的 `id` 相互对应，并且相同；否则就会导致 `BottomNavigationView` 的导航失败 ；并且item的数量不超过5个。


**4.  在Activity的XML文件中引入 `BottomNavigationView` 和 添加  `ViewPager`** 

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".NavigationUI.BottomNavigationView_ViewPager.Bnv_ViewPagerActivity">

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:layout_constraintBottom_toTopOf="@+id/nav_view2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/nav_view2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="?android:attr/windowBackground"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:labelVisibilityMode="labeled"
        app:menu="@menu/menu2" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**5. 在主 Activity 类中**


```java
public class Bnv_ViewPagerActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private Fragment One = new OneFragment();
    private Fragment Two = new TwoFragment();
    private Fragment Three = new ThreeFragment();
    private Fragment Four = new FourFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bnv__view_pager);

        initView();
        initListener();

    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.nav_view2);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {

        //监听viewPager页面变化
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                navigationView.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        //BottomNavigationView的点击监听
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                // menu/menu.xml里加的android:orderInCategory属性就是下面item.getOrder()取的值
                viewPager.setCurrentItem(menuItem.getOrder());
                return true;
            }
        });

        //ViewPager的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return One;
                    case 1:
                        return Two;
                    case 2:
                        return Three;
                    case 3:
                        return Four;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }
}
```

### [项目地址](https://gitee.com/qu-wenbin/navigation-demo)

### 参考资料
- [**Navigation导航组件使用文档**](https://developer.android.google.cn/guide/navigation)

