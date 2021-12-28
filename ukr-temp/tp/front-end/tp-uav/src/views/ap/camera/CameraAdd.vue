<template>
    <div v-loading="isLoading" element-loading-text="Loading..." element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)">
        <PageHeader page-title="Add UAV Camera Parameters" back-page-title="UAV Camera" :back-routes="['/aerial-photography/calculator/uav-camera']"/>

        <el-form label-position="top">
            <el-form-item label="UAV / Camera Name">
                <el-input v-model="uavForm.uavCameraName" clearable style="width: 400px"/>
            </el-form-item>

            <el-form :inline="true" label-position="top">
                <el-form-item label="Camera Image Size - Length">
                    <el-input v-model="uavForm.uavCameraImageSizeLength" clearable style="width: 200px">
                        <template slot="append">pixels</template>
                    </el-input>
                </el-form-item>

                <el-form-item label="Camera Image Size - Width">
                    <el-input v-model="uavForm.uavCameraImageSizeWidth" clearable style="width: 200px">
                        <template slot="append">pixels</template>
                    </el-input>
                </el-form-item>
            </el-form>

            <el-form-item label="Calculation Mode">
                <el-radio-group v-model="uavCameraParamMode">
                    <el-tooltip effect="dark" content="Known sensor size" placement="top-start">
                        <el-radio-button :label="1">Sensor Size</el-radio-button>
                    </el-tooltip>
                    <el-tooltip effect="dark" content="Known pixel size" placement="top-start">
                        <el-radio-button :label="2">Pixel Size</el-radio-button>
                    </el-tooltip>
                </el-radio-group>
            </el-form-item>

            <el-form :inline="true" label-position="top" v-if="uavCameraParamMode === 1">
                <el-form-item label="Sensor Size - Length">
                    <el-input v-model="uavForm.uavCameraSensorSizeLength" clearable style="width: 200px">
                        <template slot="append">mm</template>
                    </el-input>
                </el-form-item>

                <el-form-item label="Sensor Size - Width">
                    <el-input v-model="uavForm.uavCameraSensorSizeWidth" clearable style="width: 200px">
                        <template slot="append">mm</template>
                    </el-input>
                </el-form-item>
            </el-form>

            <el-form-item label="Pixel Size" v-if="uavCameraParamMode === 2">
                <el-input v-model="uavForm.uavCameraPixelSize" clearable style="width: 200px">
                    <template slot="append">μm</template>
                </el-input>
            </el-form-item>

            <el-form-item label="Focal Length">
                <el-input v-model="uavForm.uavCameraFocalLength" clearable style="width: 200px">
                    <template slot="append">mm</template>
                </el-input>
            </el-form-item>

        </el-form>

        <el-divider/>

        <el-row>
            <el-col :span="3" :offset="18">
                <el-button type="success" @click="submit">Submit</el-button>
            </el-col>
            <el-col :span="3">
                <el-button type="danger" @click="reset">Reset</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import PageHeader from "../../../components/PageHeader";

    export default {
        name: "CameraAdd",

        data() {
            return {
                uavForm: {
                    uavCameraName: null,
                    uavCameraImageSizeLength: null,
                    uavCameraImageSizeWidth: null,
                    uavCameraSensorSizeLength: null,
                    uavCameraSensorSizeWidth: null,
                    uavCameraPixelSize: null,
                    uavCameraFocalLength: null,
                },

                uavCameraParamMode: 1,

                isLoading: false
            };
        },

        methods: {
            reset() {
                this.$confirm('Reset all data?', 'Warning', {
                    confirmButtonText: 'Yes',
                    cancelButtonText: 'No',
                    type: 'warning'
                }).then(() => {
                    this.uavCameraParamMode = 1;

                    this.uavForm.uavCameraName = null;
                    this.uavForm.uavCameraImageSizeLength = null;
                    this.uavForm.uavCameraImageSizeWidth = null;

                    this.uavForm.uavCameraSensorSizeLength = null;
                    this.uavForm.uavCameraSensorSizeWidth = null;
                    this.uavForm.uavCameraPixelSize = null;

                    this.uavForm.uavCameraFocalLength = null;
                }).catch(() => {});
            },

            submit() {
                let result = true;
                const url = this.$TP_UAV_URL_PREFIX + "/aerial-photography/uav-camera/add";

                if (this.uavForm.uavCameraName === null || this.uavForm.uavCameraName === "") {
                    result = false;
                }
                if (this.uavForm.uavCameraImageSizeLength === null || this.uavForm.uavCameraImageSizeLength === "") {
                    result = false;
                }
                if (this.uavForm.uavCameraImageSizeWidth === null || this.uavForm.uavCameraImageSizeWidth === "") {
                    result = false;
                }
                if (this.uavForm.uavCameraFocalLength === null || this.uavForm.uavCameraFocalLength === "") {
                    result = false;
                }
                if (this.uavCameraParamMode === 1) {
                    if (this.uavForm.uavCameraSensorSizeLength === null || this.uavForm.uavCameraSensorSizeLength === "" || this.uavForm.uavCameraSensorSizeWidth === null || this.uavForm.uavCameraSensorSizeWidth === "") {
                        result = false;
                    }
                } else {
                    if (this.uavForm.uavCameraPixelSize === null || this.uavForm.uavCameraPixelSize === "") {
                        result = false;
                    }
                }

                if (result) {
                    this.$confirm('Submit?', 'Tip', {
                        confirmButtonText: 'Yes',
                        cancelButtonText: 'No',
                        type: 'info'
                    }).then(() => {
                        const that = this;

                        this.isLoading = true;

                        this.$axios.post(url, {
                            uavCameraParamMode: this.uavCameraParamMode,
                            uavCameraName: this.uavForm.uavCameraName,
                            uavCameraImageSizeLength: this.uavForm.uavCameraImageSizeLength,
                            uavCameraImageSizeWidth: this.uavForm.uavCameraImageSizeWidth,
                            uavCameraSensorSizeLength: this.uavForm.uavCameraSensorSizeLength,
                            uavCameraSensorSizeWidth: this.uavForm.uavCameraSensorSizeWidth,
                            uavCameraPixelSize: this.uavForm.uavCameraPixelSize,
                            uavCameraFocalLength: this.uavForm.uavCameraFocalLength
                        }).then((response) => {
                            that.isLoading = false;
                            if (response.data.state) {
                                if (response.data.code === "200") {
                                    that.$message({
                                        message: response.data.description,
                                        type: 'success'
                                    });
                                    that.$router.push('/aerial-photography/calculator/uav-camera');
                                }
                            }
                        }).catch(() => {});
                    }).catch(() => {});
                } else {
                    this.$message({
                        message: 'Data Input Incomplete!',
                        type: 'error'
                    });
                }

            }
        },

        components: {
            PageHeader
        }
    }
</script>

<style scoped>

</style>