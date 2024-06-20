DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_SAVING_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793212804176576514', 'HZBANKKYP', 'HZBANK_PROD_SAVING_SYNC_TIME', '杭州银行存款产品同步日期', '2024-06-04', '杭州银行存款产品同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_INS_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793213173136916482', 'HZBANKKYP', 'HZBANK_PROD_INS_SYNC_TIME', '杭州银行保险产品同步日期', '2024-06-04', '杭州银行保险产品同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_BOND_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793213469472882690', 'HZBANKKYP', 'HZBANK_PROD_BOND_SYNC_TIME', '杭州银行产品债券同步日期', '2024-06-04', '杭州银行理财债券同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_FUND_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793534773262581762', 'HZBANKKYP', 'HZBANK_PROD_FUND_SYNC_TIME', '杭州银行公募基金产品同步日期', '2024-06-04', '杭州银行公募基金产品同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_NETVALUE_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793559590305038337', 'HZBANKKYP', 'HZBANK_PROD_NETVALUE_SYNC_TIME', '杭州银行非公募产品净值同步日期', '2024-06-04', '杭州银行非公募产品净值同步日期', '1', 'WEALTH', '3', ' ');

DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_TRUST_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793212373601910785', 'HZBANKKYP', 'HZBANK_PROD_TRUST_SYNC_TIME', '杭州银行信托产品同步日期', '2024-06-04', '杭州银行信托产品同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_PROD_SNOTE_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1793211325088825346', 'HZBANKKYP', 'HZBANK_PROD_SNOTE_SYNC_TIME', '杭州银行理财产品同步日期', '2024-06-04', '杭州银行理财产品同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_WP_BRANCH_REPORT_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1790932393257984001', 'HZBANKKYP', 'HZBANK_WP_BRANCH_REPORT_SYNC_TIME', '杭州银行分支行资产配置报表同步日期', '2024-05-12', '杭州银行分支行资产配置报表同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_WP_PP_REPORT_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1790932584425971713', 'HZBANKKYP', 'HZBANK_WP_PP_REPORT_SYNC_TIME', '杭州银行客户报表同步日期', '2024-05-12', '杭州银行客户报表同步日期', '1', 'WEALTH', '3', ' ');

DELETE FROM sys_param WHERE param_id = 'HZBANK_USER_FUND_QUALIFY_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1782646965589307393', 'HZBANKKYP', 'HZBANK_USER_FUND_QUALIFY_SYNC_TIME', '杭州银行理财经理基金资质权限同步日期', '2024-05-30', '杭州银行理财经理基金资质权限同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_USER_INS_QUALIFY_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1782650322689257474', 'HZBANKKYP', 'HZBANK_USER_INS_QUALIFY_SYNC_TIME', '杭州银行理财经理保险资质权限同步日期', '2024-06-04', '杭州银行理财经理保险资质权限同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_BRANCH_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1782650697949442050', 'HZBANKKYP', 'HZBANK_BRANCH_SYNC_TIME', '杭州银行组织架构同步日期', '2024-06-04', '杭州银行组织架构同步日期', '1', 'WEALTH', '3', ' ');
DELETE FROM sys_param WHERE param_id = 'HZBANK_USER_SYNC_TIME' AND tenant_code = 'HZBANKKYP';
INSERT INTO sys_param (id, tenant_code, param_id, param_name, param_value, value_name, allow_modi, module, belong_type, reserve1) VALUES ('1782650861179170817', 'HZBANKKYP', 'HZBANK_USER_SYNC_TIME', '杭州银行用户信息同步日期', '2024-05-30', '杭州银行用户信息同步日期', '1', 'WEALTH', '3', ' ');

update WP_RESTFUL_ESB_INFO set EURAKE_URL = 'http://158.1.7.183:8588/dataapi/api/query';
commit;
update sys_param set param_value = 'A21_CFZP_RPT_CUST_REPORT,ETL_RDM_DEV' where param_id in ('HZBANK_SOURCE_WP_BRANCH_REPORT_TABLE','HZBANK_SOURCE_WP_PP_REPORT_TABLE')
update sys_param set param_value = '2024-04-22' where tenant_code = 'HZBANKKYP' and param_id in ('HZBANK_CUST_QUERY_TIME', 'HZBANK_CUST_QUOTA_QUERY_TIME', 'HZBANK_CUST_PROQUOTA_QUERY_TIME');
