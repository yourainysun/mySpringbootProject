package com.work.springbootinit.model.entity;


public class SysBranch {

  private String branchCode;
  private String parentBranchCodes;


  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }


  public String getParentBranchCodes() {
    return parentBranchCodes;
  }

  public void setParentBranchCodes(String parentBranchCodes) {
    this.parentBranchCodes = parentBranchCodes;
  }

}
