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
        //获取子类节点component对象
        if (this.data.childItem.length == this.data.activeIndex){
          child.setData({show:true});
        }
        this.data.childItem.push(child);
      }
    }
  },


  attached:function(){
    this.setData({ barWidth: wx.getSystemInfoSync().windowWidth / this.data.tabs.length+"px"});
    
  },
  /**
   * 组件的初始数据
   */
  data: {
    activeIndex:0,
    sliderOffset: 0,
    sliderLeft: 0,
    barWidth:"7em",
    childItem:[],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    tabClick: function (e) {
      let activeIndex = e.currentTarget.id;
      this.setData({
        sliderOffset: e.currentTarget.offsetLeft,
        activeIndex: activeIndex
      });
      let childItem = this.data.childItem;
      for (let ix in childItem){
        childItem[ix].setData({ show: false });
      }
      if (childItem[activeIndex]){
        childItem[activeIndex].setData({ show: true });
      }
    
    }
  }
})
