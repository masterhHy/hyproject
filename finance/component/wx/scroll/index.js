// component/wx/scroll/index.js
Component({
  /**
   * 组件的属性列表
   * 使用组件是，后台返回的呈现数据放在json里 且key 为data
   * 
   */
  properties: {
    isAjax:{
      type: Boolean,
      value: false
    },
    url:{
      type: String,
      value: ""
    },
    method:{
      type: String,
      value: "POST"
    },
    param:{
      type: JSON,
      value: {}
    },
    pageSize:{
      type:Number,
      value:5
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
    loading:false,
    noMoreData: false,
    pageNo:1,
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
        this.data.param.pageSize = this.data.pageSize;
        this.data.param.pageNo = this.data.pageNo;
        this.setData({ loading: true });    
        wx.request({
          url: this.data.url,
          data:this.data.param,
          method:this.data.method,
          success:(res)=>{
            if(res.data&&res.data.length==0){
              this.setData({ noMoreData: true });
            }else{
              this.setData({ pageNo: this.data.pageNo++ })
              this.triggerEvent("downLoading", res);
            }
           
          },
          fail:function(){
            wx.showToast({
              title: '后台接口出问题',
              icon:"none"
            });
          },
          complete:()=>{
            this.setData({ loading: false });
          }
        });
        
        
        
      }
    }
  }
})
