// pages/company/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabs:["公司分析","投资者分析"],
    company:{
      data:[],
      loading:false,
      noMoreData:false
    },
    inventor:{
      data: [],
      loading: false,
      noMoreData: false
    },
  },
  ichange(e){
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    for (let i = 0; i < 50; i++) {
      this.data.company.data.push(i);

    }
    this.setData({ 'company.data': this.data.company.data });
    for (let i = 0; i < 25; i++) {
      this.data.inventor.data.push(i);

    }
    this.setData({ 'inventor.data': this.data.inventor.data });
  },

  downLoading(e){
    
    setTimeout(()=>{
      this.setData({"company.loading":false});
      for (let i = 50; i < 100; i++) {
        this.data.inventor.data.push(i);

      }
      this.setData({ 'company.data': this.data.inventor.data });
    },3000);
  },
  
  

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})