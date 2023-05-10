package action.fim.acInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 항공기 관리 - 서비스
 * @author Ryoma
 *
 */
@Service(value = "acInfoService")
public class AcInfoService {
	
	@Resource(name = "acInfoMapper")
	private AcInfoMapper acInfoDao;

	/**
	 * 항공기 정보를 등록한다.
	 * @param params
	 * @return
	 */
	public void insertAcInfo(Map params) {
		
//		try {
		System.out.println("asdasdasdasd");
//			// 항공기 호기  중복 체크
//			if(acInfoDao.acNoDupCheckCount(params) > 0) {
//				return super.errorResult( super.getMessage( "중복된 항공기 호기가 존재합니다.", null, params.getLang() ) );
//			}
//			acInfoDao.insert(params);
//
//			// TO-DO : 항공기 사진(첨부파일) 수정 로직 추가
//			Map dspnHstGrid = params.getGridData("dspnHstGrid");
//
//			List insDspnList = (List) dspnHstGrid.get("insList");
//
//			for(int i=0; i<insDspnList.size(); i++) {
//				Map insItem = (Map) insDspnList.get(i);
//				Params dspnParams = new Params();
//				dspnParams.put("AC_SEQ", params.getString("AC_SEQ"));
//				dspnParams.put("AC_DSPN_DT", insItem.get("AC_DSPN_DT"));
//				dspnParams.put("AC_DSPN_PLC", insItem.get("AC_DSPN_PLC"));
//				dspnParams.put("AC_DSPN_REM", insItem.get("AC_DSPN_REM"));
//
//				acInfoDao.insertAcDspnHst(dspnParams);
//			}
//
//			fileInfoService.savFiles(params, "files", params.getString("AC_SEQ"));
			
//		}
		
//    	catch( RuntimeException e ) { super.error( e.getMessage(), e ); return super.errorResult( super.getMessage( "E0001", null, params.getLang() ) ); }
//		catch(Exception e) {
//			super.error( e.getMessage(), e );
//			return super.errorResult( super.getMessage( "E0001", null, params.getLang() ) );
//		}
//
//		return super.successResult(super.getMessage("M0002", null, params.getLang()));
	}
}
