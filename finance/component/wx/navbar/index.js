// component/wx/navbar/index.js
Component({
  /**
   * 组件的属性列表
   */
  externalClasses: ['bg-class',"fz-class"],
 
  properties: {
    tabs:{
      type:Array,
      value: []
    } 
    
  },
  relations: {
    '../navbar_item/index': {
      type: 'child', 
      linked: function (child) {
        
      }
    }
  },


  attached:function(){
    let width = wx.getSystemInfoSync().windowWidth;
    this.setData({ windowWith: width });
    this.setData({ barWidth: width / this.data.tabs.length});

    this.watchActiveIndex();

  },
  
  /**
   * 组件的初始数据
   */
  data: {
    sliderOffset: 0,
    sliderLeft: 0,
    barWidth:0,

    windowWith: 0,//常亮，可视窗口
    activeIndex: 0,//当前活跃选项卡
    scrollOffset:0,//横向滚动窗口偏移

  },

  /**
   * 组件的方法列表
   */
  methods: {
    tabClick: function (e) {
      let activeIndex = e.currentTarget.id;
      this.setData({activeIndex: activeIndex});
      this.watchActiveIndex();
     
    },
    watchActiveIndex(){
      this.setData({ sliderOffset: this.data.barWidth * this.data.activeIndex })
      this.setData({ scrollOffset: this.data.windowWith * this.data.activeIndex });
      this.data.currentLeft = this.data.scrollOffset;
    },
    
    itemScroll(e){
      //判断滚动往左还是往右
      let currentLeft = e.detail.scrollLeft;
      var dir ="";
      if ((this.data.currentLeft || 0) > currentLeft){
        //往左
        dir="left";
      }else{
        dir = "right";
      }
      
      //判断当容器在哪个区间
      let cindex = Math.floor(currentLeft / this.data.windowWith);
      let sliderPercent = (currentLeft % this.data.windowWith) / this.data.windowWith;
      let change =false;
      if (dir =="right"){
        if (sliderPercent>0.5){
          cindex+=1;
          change=true;
        }
      } else if (dir == "left"){ 
        if (sliderPercent<0.5){
          change = true;
        }  
      }
      if (cindex<0){
        cindex=0;
        change=false;
      }
      if (cindex > this.data.tabs.length-1){
        cindex = this.data.tabs.length;
        change = false;
      }

      if (change){
        this.setData({ activeIndex: cindex });
        this.setData({ sliderOffset: this.data.barWidth * this.data.activeIndex })
      }

      this.data.currentLeft = currentLeft;
      setTimeout(() => {
        if (this.data.currentLeft == currentLeft) {
         //当滑动停下来时触发
          this.watchActiveIndex();
        }
      }, 500);
      


    }
  }
})
