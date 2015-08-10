/**
 * move menu function
 */
/**
 * move menu function
 * @param id
 * 1xx : production
 * 100 : production list
 * 101 : production detail
 * 102 : production tag config
 */
function moveToMenu(id){
	var url = "";
	switch(id){
		case 100 : url = ""; break;
		case 101 : url = ""; break;
		case 102 : url = "/production/settingTopClothes"; break;
		case 103 : url = "/production/settingPantsClothes"; break;
	}
	location.href=url;
}
