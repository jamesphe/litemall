var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var check = require('../../../utils/check.js');
var area = require('../../../utils/area.js');

var app = getApp();
Page({
  data: {
    companyInvoice: false,
    activeStyle: "background-color: red;color: white;",
    inActiveStyle: "background-color: #f4f4f4;color: #000;",
    personTitleStyle:"",
    companyTitleStyle:"",
    invoiceTitle:"",
    invoiceTaxno:"",
    invoiceAddress: '',
    invoicePhone: '',
    invoiceBank: '',
    invoiceAccount: '',
    invoiceEmail: ''
  },
  bindinputInvoiceTitle(event){
    this.setData({
      invoiceTitle:event.detail.value
    })
  },
  bindinputInvoiceTaxno(event){
    this.setData({
      invoiceTaxno:event.detail.value
    })
  },
  bindinputInvoiceAddress(event){
    this.setData({
      invoiceAddress:event.detail.value
    })
  },
  bindinputInvoicePhone(event){
    this.setData({
      invoicePhone:event.detail.value
    })
  },
  bindinputInvoiceBank(event){
    this.setData({
      invoiceBank:event.detail.value
    })
  },
  bindinputInvoiceAccount(event){
    this.setData({
      invoiceAccount:event.detail.value
    })
  },
  bindinputInvoiceEmail(event){
    this.setData({
      invoiceEmail:event.detail.value
    })
  },
  setPersonTitle(){
    this.setData({
      companyInvoice: false,
      personTitleStyle:this.data.activeStyle,
      companyTitleStyle:this.data.inActiveStyle
    });
  },
  setCompanyTitle(){
    this.setData({
      companyInvoice: true,
      personTitleStyle:this.data.inActiveStyle,
      companyTitleStyle:this.data.activeStyle
    });
  },
  confirmInvoice(){
    var pages=getCurrentPages();
    var prevPage=pages[pages.length-2];
    prevPage.setData({
      invoiceType:1,
      invoiceTitle: this.data.invoiceTitle,
      invoiceTaxno: this.data.invoiceTaxno,
      invoiceAddress: this.data.invoiceAddress,
      invoicePhone: this.data.invoicePhone,
      invoiceBank: this.data.invoiceBank,
      invoiceAccount: this.data.invoiceAccount,
      invoiceEmail: this.data.invoiceEmail,
      isCompanyInvoice: this.data.companyInvoice
    });
    wx.navigateBack();
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    console.log(options)
  },
  onReady: function() {
    this.setData({
      companyInvoice: false,
      personTitleStyle:this.data.activeStyle,
      companyTitleStyle:this.data.inActiveStyle
    });
  },
  onShow: function() {
    // 页面显示
  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  }
})
