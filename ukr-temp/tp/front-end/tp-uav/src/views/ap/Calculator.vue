<template>
    <div v-loading="isLoading" element-loading-text="Loading..." element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)">
        <PageHeader page-title="Aerial Photography Calculator" back-page-title="Home" :back-routes="['/', '/index', '/home']"/>

        <el-row>
<!--            <el-col :span="3" :offset="15">-->
<!--                <div>-->
<!--                    <el-button type="success" icon="el-icon-notebook-1" @click="navigationToRecordPage">Record</el-button>-->
<!--                </div>-->
<!--            </el-col>-->

            <el-col :span="3" :offset="18">
                <div>
                    <el-button type="danger" icon="el-icon-camera" @click="navigationToUAVCameraPage">UAV Camera Information</el-button>
                </div>
            </el-col>
        </el-row>

        <el-divider/>

        <el-descriptions title="Input"/>

        <el-form label-position="top">

            <el-form-item label="UAV Camera">
                <el-select v-if="uavForm.uavCameraList.length === 0" value="null" disabled>
                    <el-option label="No data" value="null"/>
                </el-select>
                <el-select v-else v-model="uavCurrentCameraSelectedIndex" placeholder="Select UAV Camera" @change="getCurrentUAVCameraSelected()">
                    <el-option v-for="(item, index) in uavForm.uavCameraList" :label="item.uavCameraName" :value="index"/>
                </el-select>

                <el-divider direction="vertical"/>

                <el-button v-if="uavForm.uavCameraList.length === 0 || uavCurrentCameraSelectedIndex === null" type="text" disabled>Detail</el-button>
                <el-button v-else type="text" @click="showUAVCameraDetail()">Show Camera Detail</el-button>
            </el-form-item>

            <el-form-item label="Calculation Mode">
                <el-radio-group v-model="uavCalculationMode">
                    <el-tooltip effect="dark" content="Flying Height as input, GSD as output" placement="top-start">
                        <el-radio-button :label="1">Flying Height</el-radio-button>
                    </el-tooltip>
                    <el-tooltip effect="dark" content="GSD as input, Flying Height as output" placement="top-start">
                        <el-radio-button :label="2">GSD</el-radio-button>
                    </el-tooltip>
                </el-radio-group>
            </el-form-item>

            <el-form :inline="true" label-position="top">
                <el-form-item label="Flying Speed">
                    <el-input v-model="uavForm.uavFlySpeed" clearable style="width: 300px">
                        <template slot="append">m/s</template>
                    </el-input>
                </el-form-item>

                <el-form-item label="Flying Height" v-if="uavCalculationMode === 1">
                    <el-input v-model="uavForm.uavFlyingHeight" clearable style="width: 300px">
                        <template slot="append">m</template>
                    </el-input>
                </el-form-item>

                <el-form-item label="GSD" v-if="uavCalculationMode === 2">
                    <el-input v-model="uavForm.uavGSD" clearable style="width: 300px">
                        <template slot="append">m</template>
                    </el-input>
                </el-form-item>
            </el-form>

            <el-form :inline="true" label-position="top">
                <el-form-item label="Longitudinal Overlap">
                    <el-input v-model="uavForm.uavLongitudinalOverlap" clearable style="width: 300px">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>

                <el-form-item label="Lateral Overlap">
                    <el-input v-model="uavForm.uavLateralOverlap" clearable style="width: 300px">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
            </el-form>

            <el-row>
                <el-col :span="3" :offset="18">
                    <el-button type="success" @click="calculate">Calculate</el-button>
                </el-col>

                <el-col :span="3">
                    <el-button type="danger" @click="reset">Reset</el-button>
                </el-col>
            </el-row>

            <el-divider/>

            <el-descriptions class="margin-top" title="Output" :column="2" direction="vertical" border>
                <template slot="extra">
<!--                    <el-button type="primary" size="small" @click="saveResult">Save Result</el-button>-->
                </template>

                <el-descriptions-item v-if="uavCalculationMode === 1" :span="2">
                    <template slot="label">GSD / m</template>
                    <span v-if="uavForm.uavGSD === null">No Result</span>
                    <span v-else v-text="uavForm.uavGSD"></span>
                </el-descriptions-item>

                <el-descriptions-item v-if="uavCalculationMode === 2" :span="2">
                    <template slot="label">Flying Height / m</template>
                    <span v-if="uavForm.uavFlyingHeight === null">No Result</span>
                    <span v-else v-text="uavForm.uavFlyingHeight"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="1">
                    <template slot="label">Actual Size - Length / m</template>
                    <span v-if="uavForm.uavActualSizeLength === null">No Result</span>
                    <span v-else v-text="uavForm.uavActualSizeLength"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="1">
                    <template slot="label">Actual Size - Width / m</template>
                    <span v-if="uavForm.uavActualSizeWidth === null">No Result</span>
                    <span v-else v-text="uavForm.uavActualSizeWidth"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="1">
                    <template slot="label">Photography Distance / m</template>
                    <span v-if="uavForm.uavPhotographyDistance === null">No Result</span>
                    <span v-else v-text="uavForm.uavPhotographyDistance"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="1">
                    <template slot="label">Route Distance / m</template>
                    <span v-if="uavForm.uavRouteDistance === null">No Result</span>
                    <span v-else v-text="uavForm.uavRouteDistance"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Photo Time Interval / s</template>
                    <span v-if="uavForm.uavPhotoTimeInterval === null">No Result</span>
                    <span v-else v-text="uavForm.uavPhotoTimeInterval"></span>
                </el-descriptions-item>
            </el-descriptions>

        </el-form>

        <el-dialog title="Detail" :visible.sync="dialogVisible">
            <el-descriptions class="margin-top" title="" :column="2" border>
                <el-descriptions-item :span="2">
                    <template slot="label">Name</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraName"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Created Time</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraCreateTime"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Image Length</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraImageSizeLength"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Image Width</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraImageSizeWidth"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Sensor Length</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraSensorSizeLength"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Sensor Width</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraSensorSizeWidth"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Pixel Size</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraPixelSize"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Focal Length</template>
                    <span v-text="uavForm.uavCurrentCameraSelectedDetail.uavCameraFocalLength"></span>
                </el-descriptions-item>
            </el-descriptions>
        </el-dialog>

    </div>
</template>

<script>
    import PageHeader from "../../components/PageHeader";

    import * as math from "mathjs";

    export default {
        name: "Calculator",

        data() {
            return {
                uavForm: {
                    uavCameraList: [],

                    uavCurrentCameraSelected: {
                        uavCameraId: null,
                        uavCameraName: null,
                        uavCameraImageSizeLength: null,
                        uavCameraImageSizeWidth: null,
                        uavCameraSensorSizeLength: null,
                        uavCameraSensorSizeWidth: null,
                        uavCameraFocalLength: null,
                        uavCameraPixelSize: null,
                        uavCameraCreateTime: null
                    },

                    uavCurrentCameraSelectedDetail: {
                        uavCameraId: null,
                        uavCameraName: null,
                        uavCameraImageSizeLength: null,
                        uavCameraImageSizeWidth: null,
                        uavCameraSensorSizeLength: null,
                        uavCameraSensorSizeWidth: null,
                        uavCameraFocalLength: null,
                        uavCameraPixelSize: null,
                        uavCameraCreateTime: null
                    },

                    uavFlyingHeight: null,
                    uavFlySpeed: null,

                    uavLongitudinalOverlap: null,
                    uavLateralOverlap: null,

                    uavGSD: null,
                    uavActualSizeLength: null,
                    uavActualSizeWidth: null,
                    uavPhotographyDistance: null,
                    uavRouteDistance: null,
                    uavPhotoTimeInterval: null
                },

                uavCalculationMode: 1,

                uavCurrentCameraSelectedIndex: null,

                isLoading: false,

                dialogVisible: false
            }
        },

        methods: {
            getCurrentUAVCameraSelected() {
                let i = this.uavCurrentCameraSelectedIndex;

                this.uavForm.uavCurrentCameraSelected.uavCameraCreateTime = this.uavForm.uavCameraList[i].uavCameraCreateTime;
                this.uavForm.uavCurrentCameraSelected.uavCameraId = this.uavForm.uavCameraList[i].uavCameraId;
                this.uavForm.uavCurrentCameraSelected.uavCameraName = this.uavForm.uavCameraList[i].uavCameraName;
                this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength = this.uavForm.uavCameraList[i].uavCameraImageSizeLength;
                this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth = this.uavForm.uavCameraList[i].uavCameraImageSizeWidth;
                this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength = this.uavForm.uavCameraList[i].uavCameraFocalLength;

                this.uavForm.uavCurrentCameraSelected.uavCameraPixelSize = this.uavForm.uavCameraList[i].uavCameraPixelSize;
                this.uavForm.uavCurrentCameraSelected.uavCameraSensorSizeLength = this.uavForm.uavCameraList[i].uavCameraSensorSizeLength;
                this.uavForm.uavCurrentCameraSelected.uavCameraSensorSizeWidth = this.uavForm.uavCameraList[i].uavCameraSensorSizeWidth;


                this.uavForm.uavCurrentCameraSelectedDetail.uavCameraId = this.uavForm.uavCameraList[i].uavCameraId;
                this.uavForm.uavCurrentCameraSelectedDetail.uavCameraName = this.uavForm.uavCameraList[i].uavCameraName;
                this.uavForm.uavCurrentCameraSelectedDetail.uavCameraImageSizeLength = this.uavForm.uavCameraList[i].uavCameraImageSizeLength + " pixels";
                this.uavForm.uavCurrentCameraSelectedDetail.uavCameraImageSizeWidth = this.uavForm.uavCameraList[i].uavCameraImageSizeWidth +" pixels";
                this.uavForm.uavCurrentCameraSelectedDetail.uavCameraFocalLength = this.uavForm.uavCameraList[i].uavCameraFocalLength + " mm";
                this.uavForm.uavCurrentCameraSelectedDetail.uavCameraCreateTime = this.uavForm.uavCameraList[i].uavCameraCreateTime;

                if (this.uavForm.uavCameraList[i].uavCameraPixelSize === null) {
                    this.uavForm.uavCurrentCameraSelectedDetail.uavCameraSensorSizeLength = this.uavForm.uavCameraList[i].uavCameraSensorSizeLength + " mm";
                    this.uavForm.uavCurrentCameraSelectedDetail.uavCameraSensorSizeWidth = this.uavForm.uavCameraList[i].uavCameraSensorSizeWidth + " mm";
                    this.uavForm.uavCurrentCameraSelectedDetail.uavCameraPixelSize = math.round(math.divide(math.bignumber(this.uavForm.uavCameraList[i].uavCameraSensorSizeLength + ""), math.bignumber(this.uavForm.uavCameraList[i].uavCameraImageSizeLength + "")) * 1000, 4) + " μm (Calculated)";
                } else {
                    this.uavForm.uavCurrentCameraSelectedDetail.uavCameraPixelSize = this.uavForm.uavCameraList[i].uavCameraPixelSize + " μm";
                    this.uavForm.uavCurrentCameraSelectedDetail.uavCameraSensorSizeLength = math.round(this.uavForm.uavCameraList[i].uavCameraPixelSize * this.uavForm.uavCameraList[i].uavCameraImageSizeLength / 1000, 0) + " mm (Calculated)";
                    this.uavForm.uavCurrentCameraSelectedDetail.uavCameraSensorSizeWidth = math.round(this.uavForm.uavCameraList[i].uavCameraPixelSize * this.uavForm.uavCameraList[i].uavCameraImageSizeWidth / 1000, 0) + " mm (Calculated)";
                }

            },

            showUAVCameraDetail() {
                this.dialogVisible = true;
            },

            calculate() {
                let result = true;

                if (this.uavCurrentCameraSelectedIndex === null) {
                    result = false;
                }

                if (this.uavForm.uavFlySpeed === null || this.uavForm.uavFlySpeed === "") {
                    result = false;
                }

                if (this.uavForm.uavLongitudinalOverlap === null || this.uavForm.uavLongitudinalOverlap === "") {
                    result = false;
                }

                if (this.uavForm.uavLateralOverlap === null || this.uavForm.uavLateralOverlap === "") {
                    result = false;
                }

                if (this.uavCalculationMode === 1) {
                    if (this.uavForm.uavFlyingHeight === null || this.uavForm.uavFlyingHeight === "") {
                        result = false;
                    }
                } else {
                    if (this.uavForm.uavGSD === null || this.uavForm.uavGSD === "") {
                        result = false;
                    }
                }

                if (!result) {
                    this.$message({
                        message: 'Data Input Incomplete!',
                        type: 'error'
                    });
                } else {
                    let ps;

                    if (this.uavForm.uavCurrentCameraSelected.uavCameraPixelSize === null) {
                        ps = math.divide(math.bignumber(this.uavForm.uavCurrentCameraSelected.uavCameraSensorSizeLength + ""), math.bignumber(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength + ""));
                    } else {
                        ps = this.uavForm.uavCurrentCameraSelected.uavCameraPixelSize;
                    }

                    if (this.uavCalculationMode === 1) {
                        // ps: math.divide(math.bignumber(this.uavForm.uavCurrentCameraSelected.uavCameraSensorSizeLength + ""), math.bignumber(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength + ""))
                        this.uavForm.uavGSD = math.round(this.uavForm.uavFlyingHeight * ps / this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength, 4);
                        this.uavForm.uavActualSizeLength = math.round(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength * (this.uavForm.uavFlyingHeight * ps / this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength), 0);
                        this.uavForm.uavActualSizeWidth = math.round(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth * (this.uavForm.uavFlyingHeight * ps / this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength), 0);
                        this.uavForm.uavPhotographyDistance = math.round(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth * (this.uavForm.uavFlyingHeight * ps / this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength) * (1 - (this.uavForm.uavLongitudinalOverlap / 100)), 0);
                        this.uavForm.uavRouteDistance = math.round(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength * (this.uavForm.uavFlyingHeight * ps / this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength) * (1 - (this.uavForm.uavLateralOverlap / 100)), 0);
                        this.uavForm.uavPhotoTimeInterval = math.round(math.divide(this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth * (this.uavForm.uavFlyingHeight * ps / this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength) * (1 - (this.uavForm.uavLongitudinalOverlap / 100)), this.uavForm.uavFlySpeed), 2);
                    } else {
                        this.uavForm.uavFlyingHeight = math.round(math.divide(this.uavForm.uavGSD * this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength, ps), 0);
                        this.uavForm.uavActualSizeLength = math.round(this.uavForm.uavGSD * this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength, 0);
                        this.uavForm.uavActualSizeWidth = math.round(this.uavForm.uavGSD * this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth, 0);
                        this.uavForm.uavPhotographyDistance = math.round(this.uavForm.uavGSD * this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth * (1 - (this.uavForm.uavLongitudinalOverlap / 100)), 0);
                        this.uavForm.uavRouteDistance = math.round(this.uavForm.uavGSD * this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength * (1 - (this.uavForm.uavLateralOverlap / 100)), 0);
                        this.uavForm.uavPhotoTimeInterval = math.round(math.divide(this.uavForm.uavGSD * this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth * (1 - (this.uavForm.uavLongitudinalOverlap / 100)), this.uavForm.uavFlySpeed), 2);
                    }



                }
            },

            reset() {
                this.$confirm('Reset all data?', 'Warning', {
                    confirmButtonText: 'Yes',
                    cancelButtonText: 'No',
                    type: 'warning'
                }).then(() => {
                    this.uavForm.uavCurrentCameraSelected.uavCameraId = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraName = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeLength = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraImageSizeWidth = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraSensorSizeLength = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraSensorSizeWidth = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraFocalLength = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraPixelSize = null;
                    this.uavForm.uavCurrentCameraSelected.uavCameraCreateTime = null;

                    this.uavForm.uavFlyingHeight = null;
                    this.uavForm.uavFlySpeed = null;
                    this.uavForm.uavLongitudinalOverlap = null;
                    this.uavForm.uavLateralOverlap = null;
                    this.uavForm.uavGSD = null;
                    this.uavForm.uavActualSizeLength = null;
                    this.uavForm.uavActualSizeWidth = null;
                    this.uavForm.uavPhotographyDistance = null;
                    this.uavForm.uavRouteDistance = null;
                    this.uavForm.uavPhotoTimeInterval = null;

                    this.uavCalculationMode = 1;

                    this.uavCurrentCameraSelectedIndex = null;
                }).catch(() => {});
            },

            saveResult() {
                this.$confirm('Save the current result?', 'Tip', {
                    confirmButtonText: 'Yes',
                    cancelButtonText: 'No',
                    type: 'info'
                }).then(() => {
                    // TODO 保存结果
                }).catch(() => {});
            },

            navigationToRecordPage() {
                if (this.$route.path !== '/aerial-photography/calculator/record') {
                    this.$router.push('/aerial-photography/calculator/record');
                }
            },

            navigationToUAVCameraPage() {
                if (this.$route.path !== '/aerial-photography/calculator/uav-camera') {
                    this.$router.push('/aerial-photography/calculator/uav-camera');
                }
            }
        },

        created() {
            const url = this.$TP_UAV_URL_PREFIX + "/aerial-photography/uav-camera/all";
            const that = this;

            this.isLoading = true;

            this.$axios.get(url).then((response) => {
                that.isLoading = false;

                if (response.data.state) {
                    if (response.data.code === "200") {
                        let arr = response.data.data;

                        if (arr.length === 0) {
                            that.uavForm.uavCameraList = [];
                        } else {
                            for (let i = 0; i < arr.length; i++) {
                                let obj = {};

                                obj.uavCameraId = arr[i].cpUuid;
                                obj.uavCameraName = arr[i].cpName;
                                obj.uavCameraImageSizeLength = arr[i].cpImageSizeLength;
                                obj.uavCameraImageSizeWidth = arr[i].cpImageSizeWidth;
                                obj.uavCameraSensorSizeLength = arr[i].cpImageSensorSizeLength;
                                obj.uavCameraSensorSizeWidth = arr[i].cpImageSensorSizeWidth;
                                obj.uavCameraPixelSize = arr[i].cpPixelSize;
                                obj.uavCameraFocalLength = arr[i].cpFocalLength;
                                obj.uavCameraCreateTime = arr[i].cpCreateTime;
                                obj.uavCameraIndex = i;

                                that.uavForm.uavCameraList.push(obj);
                            }
                        }
                    }
                }
            }).catch(() => {});
        },

        components: {
            PageHeader
        }
    }
</script>

<style scoped>

</style>