// component/wx/scroll/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    isAjax:{
      type: Boolean,
      value: false
    },
    loading: {
      type: Boolean,
      value: false
    },
    noMoreData: {
      type: Boolean,
      value: false
    }
  },
  ready() {
    this.createSelectorQuery().select(".js_scroll").fields({
      rect: true
    }, res => {
      let top = res.top;
      this.setData({ height: wx.getSystemInfoSync().windowHeight - top });
    }).exec();

  },

  /**
   * 组件的初始数据
   */
  data: {
    height: 0,
  },

  /**
   * 组件的方法列表
   */
  methods: {
    upLoading(e) {
      this.triggerEvent("upLoading", e.detail);
      
    },
    downLoading(e) {
      if (this.data.isAjax && !this.data.noMoreData && !this.data.loading) {
        setTimeout(()=>{
          this.triggerEvent("downLoading", e.detail);
        },500)
        this.setData({loading:true});
      }
      console.log(e)
    }
  }
})
