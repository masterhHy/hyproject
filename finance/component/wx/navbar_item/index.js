// component/wx/navbar_item/index.js
Component({

  relations: {
    '../navbar/index': {
      type: 'parent', 
      linked: function (target) {
        
      }
    }
  },
  

  /**
   * 组件的属性列表
   */
  properties: {
    url: {
      type: String,
      value: ""
    },
    method: {
      type: String,
      value: "GET"
    },
    param: {
      type: JSON,
      value: {}
    },
    pageSize: {
      type: Number,
      value: 5
    },
    isAjax: {
      type: Boolean,
      value: false
    },
  },

  /**
   * 组件的初始数据
   */
  data: {
    
  },

  /**
   * 组件的方法列表
   */
  methods: {
    upLoading(e) {
      this.triggerEvent("upLoading", e.detail);

    },
    downLoading(e) {
      this.triggerEvent("downLoading", e.detail);
    }
  }
})
