// pages/company/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabs:["111","222","333","444"],
    mydata:[],
    mydata2: []
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
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    for (let i = 0; i < 100; i++) {
      this.data.mydata.push(i);

    }
    this.setData({ 'mydata': this.data.mydata });
    for (let i = 0; i < 50; i++) {
      this.data.mydata2.push(i);

    }
    this.setData({ 'mydata2': this.data.mydata2 });
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