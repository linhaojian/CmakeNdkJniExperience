# CmakeNdkJniExperience
体验Cmake,实现c/c++与java之间的相互调用。

# 介绍关键词 Jni Ndk Cmake
  Cmake :cmake是一个跨平台、开源的构建系统。它是一个集软件构建、测试、打包与一身的软件。它使用与平台和编译器独立的配置文件来对软件编译过程进
行控制。现在许多跨平台的开源软件都转向了用cmake来做构建工具，如KDE，Kdevelop，hypertable等，使用cmake，你可以不用关心如何去创建编译可执行文
件和动态库。它为了支持不同的平台，提供了以下特性：跨库依赖检查，并行构建和简单的头文件结构，这使它大大减少了跨平台软件的开发和维护过程的复杂性。
(简单来说就是把我们之前jni/ndk开发的流程简化了)。

  Jni :JNI是Java Native Interface的缩写，中文为JAVA本地调用。使用JNI, 可以调用用其他语言写成的库或程序,尤其是C和C++。
  
  Ndk :全称 Native Development Kit（为了实现JNI的一种开发工具）, 允许你在 Android 应用中使用 C 或 C++ 代码，NDK 可以生成 .so 文件。
       优点：1.方便代码共享 2.增加反编译的难度 3.提高性能

# 通过Cmake完成 c/c++ 与 java 相互调用
  (ps：Camke的使用兼容Android Studio 2.2以上的版本)
  
## 使用前的准备 

   * 1.下载ndk,cmake
   
   ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/1.png)
   
   * 2.配置ndk
   
   ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/2.png)
   
   * 3.配置环境变量
   
   ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/3.png)
   
## 创建新项目
   
   ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/4.png)    
   
  可以看到有一个复选框是Include C++ Support。选中它，一路向下之后工程就创建好了。可以看到我们的工程目录如下:
  
   ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/5.png)    
   
  有三个地方需要注意，第一个是.externalNativeBuild，第二个是cpp文件夹，第三个是CMakeLists.txt文件。

     1. .externalNativeBuild文件夹：cmake编译好的文件, 显示支持的各种硬件等信息。系统生成。
     2. cpp文件夹：存放C/C++代码文件，native-lib.cpp文件是默认生成的，可更改。需要自己编写。
     3. CMakeLists.txt文件：CMake脚本配置的文件。需要自己配置编写。
        
        介绍CMakeLists.txt文件里面的配置属性：
          ** cmake_minimum_required(VERSION 3.4.1)
             CMake最小版本使用的是3.4.1。
          ** add_library()
             配置so库信息（为当前脚本文件添加库（包含自定义的c/c++文件、第三方.so文件））
             add_library
             ( 
               native-lib（c/c++文件名称）
               SHARED （这个参数表示共享so库文件，详细资料看:([C++静态库与动态库](http://www.cnblogs.com/skynet/p/3372855.html))
               src/main/cpp/testc-lib.c （c/c++文件路径）
             )
          ** find_library()
             这个方法与我们要创建的so库无关而是使用NDK的Apis或者库，
             默认情况下Android平台集成了很多NDK库文件，所以这些文件是没有必要打包到apk里面去的。
             直接声明想要使用的库名称即可（猜测：貌似是在Sytem/libs目录下）。
             在这里不需要指定库的路径，因为这个路径已经是CMake路径搜索的一部分。
          ** target_link_libraries()
             如果你本地的库（native-lib）想要调用log库的方法，那么就需要配置这个属性，意思是把NDK库关联到本地库。
          ** set_target_properties（）   
             使用第三方so库需要设置的属性 ：
               set_target_properties
               (
                imported-lib // so库的名称
                PROPERTIES IMPORTED_LOCATION // import so库
                libs/libimported-lib.so // so库路径
               )
## 运行的效果
   
 ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/6.png)

## 大家会疑问.so文件存在哪里？看下图：

 ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/7.png)
 
 如果打开了上图的路径没有发现cmake文件夹，同make project一下就出来：
 
 ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/8.png)
  

# 以上就是简单介绍了Cmake的简单创建(java调用C++)与相关知识，下面我会介绍该项目里面c/c++与java相互调用。
  
  ## java 调用 c/c++ 可以按照上面简单流程进行完成。
  
  ## c/c++ 调用 java 就要通过反射的形式。
  
     * 1.创建java文件(初始化jni功能)：
        
 ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/10.png)
      
     * 2.创建对应的testc-lib.c文件编写代码，并且在CMakeLists.txt文件中配置。
     
 ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/9.png)
      
     * 3. 创建对象，并且调用自定义的native方法（ps:有点不理解，c/c++调用java 还是先要java调用c/c++，再调用java,不是没事找是干吗？）
     
 ![](https://github.com/linhaojian/CmakeNdkJniExperience/blob/master/image/11.png)
      
  以上简单介绍cmake中 c/c++与java相互调用的实现。
  
# 分享相关文件资源：
  [http://www.chenglong.ren/2017/01/24/android%E4%B8%ADjni%E7%9A%84%E4%BD%BF%E7%94%A8/](http://www.chenglong.ren/2017/01/24/android%E4%B8%ADjni%E7%9A%84%E4%BD%BF%E7%94%A8/)
  
  [http://www.open-open.com/lib/view/open1482980512823.html](http://www.open-open.com/lib/view/open1482980512823.html)
  
  [http://www.jianshu.com/p/4eefb16d83e3](http://www.jianshu.com/p/4eefb16d83e3)
 
   
