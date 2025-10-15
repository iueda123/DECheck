# Data Extraction Report

## Study Identification

- **Study ID**: Zheng2024
- **Reference Files**: Zheng2024.pdf.md; Zheng2024_sup.pdf.md
- **Author/Journal/Year**: Zheng et al., Psychological Medicine, 2024
- **Title**: Characterizing the distinct imaging phenotypes, clinical behavior, and genetic vulnerability of brain maturational subtypes in mood disorders
- **DOI**: 10.1017/S0033291724000886

## Study Characteristics

- **Study Objective**: To quantify brain morphological heterogeneity in mood disorders (MDD and BD) by mapping individual-level gray matter volume (GMV) deviations using normative modeling, identify subtypes through unsupervised clustering, and characterize these subtypes using clinical behaviors, cell-specific transcriptomic profiles, and polygenic risk scores.
- **Study Design**: Cross-sectional
- **Study Design (Other)**: -

## Reference Cohort and Imaging

### Dataset Name

- **Answer**: Single-site dataset from First Affiliated Hospital of China Medical University, Shenyang, China
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Healthy Controls (N)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Healthy Controls (Age)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Healthy Controls (Sex)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Imaging Modality

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Analysis Level

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Preprocessing Pipeline

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Quality Checking

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Quality Checking (Detail)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Site Effect Handling

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Site Effect (Detail)


## Normative Modeling

### Model Origin

- **Answer**: New3
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 
- **Supporting Text**: Quantile regression was used to obtain a normative range of regional GMV variation as a function of age and sex descripted in a previous study (Lv et al., 2021). We positioned individuals with MDD and BD on the normative percentile charts based on HC and expressed three kinds of continuous measurement of deviation from the generated normative range
- **Page/Line**: Main paper lines 82-85

### Model Origin (Detail)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Modeling Method

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Software Tool

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Response Variable

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Predictor Variables

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Predictor Effects

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Normative Model Validation - Nuisance Structures

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Normative Model Validation - Same Domain (Non-Independent)

- **Answer**: 
- **Confidence Rating**: test
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Normative Model Validation - Same Domain (Independent)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Normative Model Validation - Different Domain

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

## Clinical Application and Analysis

### Clinical Dataset

- **Answer**: First Affiliated Hospital of China Medical University, Shenyang, China (same institution as reference cohort)
- **Confidence Rating**: High
- **Negative Answer Category**: 
- **Reason**: The clinical patient data came from the same institution where healthy controls were recruited, as explicitly stated in the methods section.
- **Supporting Text**: 
- **Page/Line**: materials/Zheng2024.pdf.md: lines 69-76; 100-108; 122-124

### Diseases Studied

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Clinical Groups (N)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Clinical Groups (Age)

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Clinical Groups (Sex)

- **Answer**: MDD: F 80 (70.2%) M 34 (29.8%); BD: F 37 (61.7%) M 23 (38.3%)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Sex distribution for each clinical diagnostic group is provided in Supplementary Table S1 with exact counts.
- **Supporting Text**: Dataset 1: MDD N=114, Gender (male/female) 34/80; BD N=60, Gender (male/female) 23/37
- **Page/Line**: Supplementary Table S1, line 69

### Deviation Metric

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Page/Line**: 

### Association Analysis



### Key Findings (Brief)



### Key Findings (Detailed)



### Key Limitations



### Application Notes



## General Notes

Single-site 3.0T GE Signa HDx; CAT12/SPM12 preprocessing with DARTEL; DK-68 ROI summarization; TIV used as covariate in analyses; no site harmonization needed due to single-site design.

---
*Generated on 2025-10-15 14:44:07*
