# KAdapter

KAdapter 是Kotlin版本RecyclerView Adapter的封装,使用DSL创建Adapter，使用更简洁的方式创建

Usage

#### 当前最新版本 1.0.4
![](https://img.shields.io/badge/QQ-1483888222-green.svg)
#### 引库

        implementation 'cn.ustory.qy:kadapter:1.0.4'



1. 创建

      - 1. 最简单创建

                var simpleAdapter: KotlinAdapter<Menu> = KAdapter(R.layout.menu_list) {

                   bindData { type, vh, data ->
                        vh.itemView.tv_title.text = data.name
                   }
                }


      或


              var myAdapter: KotlinAdapter<Person> = KAdapter {
                  layout {
                      R.layout.list_item
                  }

                  bindData { vh, data ->
                      vh.itemView.tv_text.text = data.name
                  }
              }
      
      
      - 2. 带数据创建
      
      
       var myAdapter: KotlinAdapter<Person> = KAdapter {

          layout {
              R.layout.list_item
          }

          data {
              arrayListOf(Person("张三"), Person("李四"))
          }

          bindData { vh, data ->
               vh.itemView.tv_text.text = data.name
          }
      }
      
      
      - 3. 带header footer创建
      
      
       var myAdapter: KotlinAdapter<Person> = KAdapter {

          layout {
              R.layout.list_item
          }

          data {
              arrayListOf(Person("张三"), Person("李四"))
          }

          bindData { vh, data ->
               vh.itemView.tv_text.text = data.name
          }
          
          header(R.layout.header){
              it.tv_text.text = "My name is Header"
          }
          
          footer(R.layout.footer){
              it.tv_text.text = "My name is Footer"
          }
      }

      - 4. 实现多类型布局(效果图如下)

      <div align="center">
      <img width=350" height="630" src="https://github.com/UCodeUStory/KAdapter/blob/master/demo_image.jpeg"/>
      </div>



       var mutilAdapter: KotlinAdapter<Menu> = KAdapterFactory.KAdapter {

            multiLayout {
                layout {
                    R.layout.red_layout
                }
                layout {
                    R.layout.yellow_layout
                }
                layout {
                    R.layout.blue_layout
                }
                layout {
                    R.layout.green_layout
                }
            }


            bindData { type, vh, data ->
                when (type) {
                    R.layout.red_layout -> vh.itemView.tv_item.text = data.name
                    R.layout.yellow_layout -> vh.itemView.tv_item.text = data.name
                    R.layout.blue_layout -> vh.itemView.tv_item.text = data.name
                    R.layout.green_layout -> vh.itemView.tv_item.text = data.name
                }
            }
        }




2. 使用
      
        1. 直接使用
        
           //设置点击事件
           myAdapter.onItemClick { position, view -> Toast.makeText(this, "position=" + position, Toast.LENGTH_SHORT).show() }
           myAdapter into recyclerView
        
        2. 重新设置新值使用
        
           myAdapter.data{
               arrayListOf(Person("John"), Person("Bob"))
           }
           
           myAdapter.header(R.layout.header){
               it.tv_text.text = "My name is Header"
           }
           
           myAdapter.footer(R.layout.footer){
               it.tv_text.text = "My name is Footer"
           }
           
           myAdapter into recyclerView

        3. 多类型使用场景分2种
           - 1. 数相同，布局不同，我们把这部分数据叫做原始数据


                   //根据数据中某一字段来判断选择布局
                   MISCAdapter.data(datas) {
                       if (it.type == 1) {
                           addData(R.layout.content_item1, it)
                       } else {
                           addData(R.layout.content_item, it)
                       }
                   }
           - 2. 数据不同，布局也不同

                   MISCAdapter.addOtherData(0,R.layout.first_menu,Menu())
                   //添加其他类型数据
                   MISCAdapter.addOtherData(1,R.layout.horization_list,HorizationBean(horizationDatas))

                   //给新数据赋值,当我们添加的数据是新的数据类型，用backupData来获取这个值，而不是data
                   MISCAdapter.bindData(R.layout.horization_list) { type, vh, data, backupData ->
                       bindHorizationList(vh, backupData)
                   }

                   MISCAdapter into recyclerView


API文档:

    1. 单布局

        layout{} 设置布局
        data{} 初始化数据
        bindData{type, vh, data ->} 绑定数据
        onItemClick{ position, view -> } 设置条目点击事件

        addData(T)新增一条数据
        addData(index,T)新增一条数据，到指定那个位置
        addDatas() 新增多条数据

    2. 多布局
        multiLayout{}设置多布局
        data(List<T>){ it ->}初始化数据，设置多布局规则
        bindData{type, vh, data ->} 绑定数据
        onItemClick{ position, view -> } 设置条目点击事件

        addData(type,T) 新增一条数据
        addData(index,type,T) 新增一条数据到指定那个位置
        addDatas(List<T>) 新增多条数据,多布局规则默认和初始化一样
        addOtherData(position,type,data) 在指定位置，新增一条新布局类型和数据

        给指定类型数据赋值
        bindData(type) { type, vh, data, backupData ->