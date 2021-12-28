<template>
    <div>
        <PageHeader back-page-title="Home" :back-routes="['/', '/index', '/home']" page-title="About"/>

        <el-descriptions title="Information"/>
        <el-card class="card-information">
            <div class="text item">
                Name: UAV Calculator
            </div>
            <div class="text item">
                Version: 1.0
            </div>
            <div class="text item">
                Description: Calculation of UAV parameters
            </div>
            <div class="text item">
                Authors: <el-link href="https://khai.edu/ua" type="primary" target="_blank">XAI</el-link>-<el-link href="https://education.khai.edu/department/503" type="primary" target="_blank">503</el-link>-<el-link href="https://education.khai.edu/group/555amnfj" type="primary" target="_blank">555амнFJ</el-link>
            </div>
            <div class="text item">
                Note: <span style="font-weight: bolder; color: #FF0000">The software is for studying only and not for commercial use.</span>
            </div>
        </el-card>

        <el-divider/>

        <el-descriptions title="Authors (Faculty 5, Department 503, Group 555амнFJ)"/>

        <el-row :gutter="20">
            <el-col :span="6">
                <el-card>
                    <img src="/img/about/logo_f503.webp" alt="Hongwei Liu" class="image"/>
                    <div style="padding: 15px;">
                        <span>Hongwei Liu</span>
                        <div class="bottom clearfix">
                            <el-link :underline="false" href="mailto:liu.hongwei@student.khai.edu">
                                <el-button type="danger" icon="el-icon-message" round>Email</el-button>
                            </el-link>
                        </div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="6">
                <el-card>
                    <img src="/img/about/logo_f503.webp" alt="Shiyi Wu" class="image"/>
                    <div style="padding: 15px;">
                        <span>Shiyi Wu</span>
                        <div class="bottom clearfix">
                            <el-link :underline="false" href="mailto:wu.shiyi@student.khai.edu">
                                <el-button type="danger" icon="el-icon-message" round>Email</el-button>
                            </el-link>
                        </div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="6">
                <el-card>
                    <img src="/img/about/logo_f503.webp" alt="Yueshun Yuan" class="image"/>
                    <div style="padding: 15px;">
                        <span>Yueshun Yuan</span>
                        <div class="bottom clearfix">
                            <el-link :underline="false" href="mailto:yuan.yeshun@student.khai.edu">
                                <el-button type="danger" icon="el-icon-message" round>Email</el-button>
                            </el-link>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import PageHeader from "../components/PageHeader";

    export default {
        name: 'About',
        components: {
            PageHeader
        }
    }
</script>

<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before, .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .card-information {
        width: 480px;
    }

    .image {
        width: 100%;
        height: 100%;
        display: block;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }
</style>
