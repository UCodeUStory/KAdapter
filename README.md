# KAdapter
封装Kotlin版本RecyclerView Adapter,使用DSL创建Adapter，创建Adapter就这么简单


### [KAdapter最新Jar下载](https://github.com/UCodeUStory/KAdapter/blob/master/jar/KAdapter_1.0.jar)

Usage

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

      - 4. 多类型布局


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

        3. 多类型使用

           //原始数据
           var datas = arrayListOf(Menu("Android"), Menu("IOS"), Menu("微信小程序"), Menu("HTML程序"))

           mutilAdapter.data(datas) {
               update(0,R.layout.red_layout) //设置第一个元素类型为red_layout
               update(0..1,R.layout.yellow_layout) //设置0 到 1的类型为yellow_layout，如果之前设置过0，会覆盖之前的设置的类型
               update(2,R.layout.blue_layout)
               update(3,R.layout.green_layout)
               insert(2,R.layout.red_layout,"Python")//在第2个位置后插入一个red_layout类型数据,
           }

           // 对新增red_layout类型数据单独设置，新数据使用backupData字段，因为新数据类型不确定
           mutilAdapter.bindData(R.layout.red_layout){
              type, vh, data, backupData ->
              backupData?.let {
                  vh.itemView.tv_item.text = backupData as String
              }
           }
           // 对原始旧数据重新设置，原始数据使用data字段
           mutilAdapter.bindData(R.layout.green_layout){
              type, vh, data, backupData ->
              data?.let {
                  vh.itemView.tv_item.text = "新·"+data.name
              }
           }

           mutilAdapter into recyclerView
        
