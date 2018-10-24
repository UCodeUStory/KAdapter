# KAdapter
封装Kotlin版本RecyclerView Adapter,使用DSL创建Adapter，创建Adapter就这么简单


Usage

1. 创建

      - 1. 最简单创建

                var simpleAdapter: KotlinAdapter<Menu> = KAdapter(R.layout.menu_list) {

                   bindData { type, vh, data ->
                       vh.bindView<TextView>(R.id.tv_title).text = data.name
                   }
                }


      或


              var myAdapter: KotlinAdapter<Person> = KAdapter {
                  layout {
                      R.layout.list_item
                  }

                  bindData { vh, data ->
                      vh.bindView<TextView>(R.id.tv_item).text = data.name
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
              vh.bindView<TextView>(R.id.tv_item).text = data.name
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
              vh.bindView<TextView>(R.id.tv_item).text = data.name
          }
          
          header(R.layout.header){
              it.tv_text.text = "My name is Header"
          }
          
          footer(R.layout.footer){
              it.tv_text.text = "My name is Footer"
          }
      }

      - 4. 多类型布局


       var mutilAdapter: KotlinAdapter<Menu> = KAdapterFactory.KAdapter {

            multiLayout {
                layout {
                    R.layout.list_item
                }
                layout {
                    R.layout.list_item2
                }
            }

            dataWithType {

                var mDataWithTypes: MutableMap<Int, Menu> = mutableMapOf()
                mDataWithTypes.put(R.layout.list_item, Menu("菜单1"))
                mDataWithTypes.put(R.layout.list_item2, Menu("菜单2"))
                mDataWithTypes
            }

            bindData { type, vh, data ->
                when (type) {
                    R.layout.list_item -> vh.bindView<TextView>(R.id.tv_title).text = data.name
                    R.layout.list_item2 -> vh.bindView<TextView>(R.id.tv_title).text = data.name
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
        
