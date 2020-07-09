package cn.iocoder.mall.system.rest.controller.errorCode;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeAddDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeDeleteDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodePageDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeUpdateDTO;
import cn.iocoder.mall.system.biz.service.errorcode.ErrorCodeService;
import cn.iocoder.mall.system.rest.convert.errorcode.ErrorCodeConvert;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodeAddRequest;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodePageRequest;
import cn.iocoder.mall.system.rest.response.errorcode.ErrorCodePageResponse;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodeUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 错误码
 *
 * @author youyusi
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/errorcode") // TODO FROM 芋艿 to 鱿鱼须：error-code
@Api("错误码")
public class SystemErrorCodeController { // TODO FROM 芋艿 to 鱿鱼须：变量要空行
    @Autowired
    private ErrorCodeService errorCodeService;

    @GetMapping("/page")
    @ApiOperation(value = "错误码分页")
    @RequiresPermissions("system:errorcode:page")
    public CommonResult<PageResult<ErrorCodePageResponse>> page(ErrorCodePageRequest request) {
        ErrorCodePageDTO pageDTO = ErrorCodeConvert.INSTANCE.convert(request);
        PageResult<ErrorCodeBO> pageResult = errorCodeService.getErrorCodePage(pageDTO);
        return CommonResult.success(ErrorCodeConvert.INSTANCE.convertPage(pageResult));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建错误码")
    @RequiresPermissions("system:errorcode:add")
    public CommonResult<Integer> add(ErrorCodeAddRequest request) {
        ErrorCodeAddDTO addDTO = ErrorCodeConvert.INSTANCE.convert(request);
        return CommonResult.success(errorCodeService.addErrorCode(addDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新错误码")
    @RequiresPermissions("system:errorcode:update")
    public CommonResult<Boolean> update(ErrorCodeUpdateRequest request) {
        ErrorCodeUpdateDTO updateDTO = ErrorCodeConvert.INSTANCE.convert(request);
        errorCodeService.updateErrorCode(updateDTO);
        return CommonResult.success(true);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除错误码")
    @RequiresPermissions("system:errorcode:delete")
    @ApiImplicitParam(name = "id", value = "错误码编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        ErrorCodeDeleteDTO deleteDTO = new ErrorCodeDeleteDTO().setId(id);
        errorCodeService.deleteErrorCode(deleteDTO);
        return CommonResult.success(true);
    }
}