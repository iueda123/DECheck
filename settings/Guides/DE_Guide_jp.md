# Data Extraction Guidance for Normative Modeling in Neuroimaging: A Systematic Review

-------------------

## プロンプト概要
あなたはプロのレビューアです。特にニューロイメージングにおける正規化モデリング（Normative Modeling）を扱った研究論文の質評価を行うことに長けています。この評価は、精神神経疾患とニューロイメージング研究の方法論的考察と応用に関するシステマティックレビューの一環として実施されます。

-------------------

## 評価対象のシステマティックレビュー概要（PICO）
* **P（Population）:** 精神神経疾患を有する参加者（統合失調症、うつ病、自閉症スペクトラム障害、認知症等）
* **I（Intervention/Exposure）:** ニューロイメージングデータ（MRI、PET、EEG、MEG等）への正規化モデリングの適用
* **C（Comparator）:** 従来の症例対照研究デザインや他の正規化モデリング手法（必要に応じて）
* **O（Outcome）:** 個人レベルの逸脱パターン、臨床的有用性、診断支援への応用等

-------------------

## 対象となる資料の所在

- 処理対象となる論文情報は `./materials` にあります。このフォルダにある情報すべてに基づいて次項の情報を抽出してください。
- 必要に応じてサブフォルダの内容も参照してください。

-------------------

## Data Extraction Items

### 1. Study Identification

#### 1-1. study_id
  * Unique identifier for each paper. Author name + year.
  * Data Type: string
  * Example: 
    * Rutherford2022

#### 1-2. reference_file_names
  * File names referenced in extracting this data
  * Data Type: string
  * Example: 
    * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md
  
#### 1-3. author_journal_year
  * "Author et al., Journal, Year" format
  * Data Type: string
  * Example: 
    * Rutherford et al., Communications Biology, 2022

#### 1-4. title
  * Paper title (as original)
  * Data Type: string

#### 1-5. doi
  * DOI (10.xxxx format, NR if absent)
  * Data Type: string

### 2. Study Characteristics

#### 2-1. study_objective
  * Summarize primary research question/purpose in 1-2 sentences
  * Data Type: text
  * Notes: 
    * Describe the primary research question or purpose.

#### 2-2. study_design
  * Extraction Criteria: Cross-sectional / Longitudinal / Other
  * Data Type: categorical
  * Notes: Choose from the options

#### 2-3. study_design_other
  * Extraction Criteria: A description of the other study design.
  * Data Type: text
  * Notes: 
    * If "study_design" is "Other", describe the study design.
    * If "study_design" is not "Other", write "-".

### 3. Reference Cohort & Imaging

#### 3-1. dataset_name
  * Extraction Criteria: Dataset name (e.g., ADNI, UK Biobank)
  * Data Type: string
  * Example: 
    * UK Biobank
    * HCP Young Adult; HCP Aging; HCP Development; HCP Early Psychosis; 
    * ADNI
    * ENIGMA CHR-P
    * ABCD; CamCAN; CMI-HBN; CNP
    * Tipically-developing prticipants of ABIDE I and II
    * C-MIND, NDAR, ABIDE, ICBM, OASIS, AIBL, MIRIAD 
    * FCON-1000
    * IXI; NKI; OASIS3; OpenNeuro; 
    * PNC; TOP
    * Multi-site aggregated dataset (ABCD; HCP; UK Biobank; AIBL; ADNI; and 100+ other studies)    
    * ABIDE; POND; HBN; ADHD200; NIMH NDA Females with ASD; UK MRC-AIMS; UCSD Biomarkers of Autism
  * Notes:
    * データセットが多数（５個以上）の場合は、代表的なものを記述しつつ、「 Multi-site aggregated dataset (<DATASET_A>; <DATASET_B>; <DATASET_C>; <DATASET_D>; and XXX+ other studies)」と記述し、"general_notes"に把握したすべてのデータセットの名前を記述すること。

#### 3-2. hc_n
  * Extraction Criteria: Sample size of healthy controls
  * Data Type: string
  * Example: 
    * 101457
    * see Rutherford2022
  * Notes: 
    * 記載するのは解析対象者。例えば 「ABIDE提供は573人も、解析対象者569」。このような場合後者を記載。除外基準を経て最終的に残りモデリングに使用された人数を抽出する。
    * この情報が他の論文に記述されている旨が書かれている場合はその論文を特定するための情報を記述する。

#### 3-3. hc_age
  * Extraction Criteria: Mean, SD, and range age of healthy controls
  * Data Type: string
  * Example: 
    * mean 50; sd 5.4; min 39; max 64
  * Notes: 
    * 記載がない数値については可能なかぎり算出する。例えば「性別別平均・SDと男女n（470/99）に基づく加重平均とプールSD、男 17.5±8.3、女 15.6±7.0」の場合、「全体で17.17±8.12歳（n=569)」と計算する。
    * 被験者に胎児が含まれる場合は胎児は0歳として計算すること。またgeneral_notesに「health control include 115 days post-conception」などと記述すること。
    * この情報が他の論文に記述されている旨が書かれている場合はその論文を特定するための情報を記述する。

#### 3-4. hc_sex
  * Extraction Criteria: N and percentage of each sex
  * Data Type: string
  * Example: 
    * F 99 (17.4%), M 460 (82.6%)
  * Notes: HC - Sex (N, %)。不明な場合は不明と答えて欲しい。

#### 3-5. imaging_modality
  * Extraction Criteria: Imaging modalities (semicolon separated)
  * Data Type: string
  * Example: 
    * T1-weighted MRI; Diffusion MRI
    * T1-weighted MRI
    * T1-weighted MRI; T2-FLAIR (subset for pial refinement)
  * Notes: 
    * Semicolon separated list of imaging modalities.

#### 3-6. analysis_level
  * Extraction Criteria: 次から選択「Voxel-level」「ROI-level」「Vertex-level」「Network-level」「Other」
  * Data Type: categorical
  * Notes: 
    * Choose from the options

#### 3-7. preprocessing_pipeline
  * Extraction Criteria: Key software
  * Data Type: string
  * Example: 
    * FreeSurfer v6.0; fMRIPrep; Dipy
    * FreeSurfer 
    * CT extraction based on an approximately equally sized parcellation of 308 regions and a parcellation of 360 regions derived from multi-modal features extracted from the Human Connectome Project; site/sex/age/motion Euler number (FreeSurfer quality metric) covariates in models
    * FreeSurfer; Infant FreeSurfer; custom pipelines
    * FreeSurfer v6.0; Destrieux parcellation; cortical thickness and subcortical volumes
    * AssemblyNet preprocessing pipeline 
    * FSL DTIFIT for DTI; free-water modeling
    * FreeSurfer v5.3; FreeSurfer v6.0
  * Notes: 
    * Semicolon separated list of key software and libraries.

#### 3-8. quality_checking
  * Extraction Criteria: Yes / No
  * Data Type: categorical
  * Notes: 
    * Choose from the options

#### 3-9. quality_checking_detail
  * Extraction Criteria: Quality control details (metrics, exclusion numbers, etc.)
  * Data Type: text
  * Example: 
    * participants with >5% missing ROIs excluded.
    * Euler number threshold for structural MRI, mean FD threshold for fMRI, outlier volume for dMRI.
    * Outlier correction using ±2 IQR from mean; standardized preprocessing
    * Euler number (FreeSurfer quality metric) threshold for structural MRI quality control with exclusion based on outliers; mean framewise displacement as motion covariate; sensitivity analyses performed for motion and Euler number parameters
    * Combination of expert visual curation and automated metrics of image quality including Euler index filtering and FreeSurfer QC metrics
    * Visual inspection
    * automatic QC RegQCNet (Denis de Senneville et al., 2020) and a visual assessment when failed after automatic QC
    * Assessed harmonization by between-site tests; removed site differences in matched controls after harmonization
    * Euler number threshold (median-centered absolute Euler number >25), excluded 1566 (4%) subjects due to low-quality images
    * Manual QC (FSQC cutoff 2.5); Euler number covariate

  * Notes: 
    * If "quality_checking" is yes, describe key metric(s), number/percent/proportion of excluded data.
    * If "quality_checking" is no, write "-".
  
#### 3-10. site_effect_handling
  * Extraction Criteria: None / Batch-removal / Model-based / Other
  * Data Type: categorical
  * Notes: Choose from the options

#### 3-11. site_effect_handling_detail
  * Extraction Criteria: Site effect handling details (method names, batch variables, etc.)
  * Data Type: text
  * Example: 
    * Hierarchical Bayesian regression with site random effects (HBLM/HBGPM); compared to ComBat and regress-out
    * sensitivity: leave-one-site-out (eMethods 1), age/sex/site-matched (eMethods 2)
    * Scanner site included as covariate in linear mixed effect modeling (LME); normative modelling accounts for age; no ComBat
    * GAMLSS with study/site batch random effects; sex-stratified; out-of-sample centile alignment accounts for batch effects
    * PCNToolkit normative model accounting for site/scanner; reference 58,836 participants across 82 sites; local control data used for calibration
    * Lifespan modeling per dataset; external validation across domains; suggests robustness to domain shift
    * Retrospective harmonization across 13 sites
    * The study primarily models site effects via hierarchical Bayesian regression that treats scanner ID as a group effect with partial pooling and site-specific homoscedastic variance, and it evaluates fixed-effect site modeling and pooling after ComBat harmonization as comparators.
    * ComBat harmonization by site with covariates age, sex, diagnosis; BrainChart reference models account for site/scanner
  * Notes: 
    * If "site_effect_handling" is not None, describe method name and details. 
    * If ComBat or ComBat-GAM was used for site effect handling, describe batch variables such as site, center, dataset, scanner, vendor, model, imaging sequence, protocol. 
    * If hierarchical Bayesian regression (HBR) was used for site effect handling, carefully describe details.

----


### 4. Normative Modeling

#### 4-1. model_origin
  * Extraction Criteria: New / Pre-trained
  * Data Type: categorical
  * Notes: Choose from the options

#### 4-2. model_origin_detail
  * Extraction Criteria: Pre-training details for Pre-trained models
  * Data Type: text
  * Example: 
    * Modeled normal ranges of ROI units adjusted for age, by sex. Kernel-weighted estimation was used to estimate means and standard deviations. . See Anderson et al. (2019) for details. 
    * Reference normative model trained on 58,836 healthy participants from 82 sites; adapted with site controls. See Brown et al. (2019) for details. 
    * BrainChart reference GAMLSS models trained on 75,241 TD across sites/scanners. 
    * Within-study HC reference with z-scores

  * Notes: 
    * If "model_origin" is pre-trained, briefly describe the dataset and method on which the model is based, and provide information to find the original paper.
    * If "model_origin" is New, write "-".
    

#### 4-3. modeling_method
  * Extraction Criteria: Algorithm name (e.g., GPR, Deep Learning, GAMLSS)
  * Data Type: string
  * Example: 
    * HBR
    * GPR
    * Deep Learning (Autoencoder, VAE)
    * GAMLSS
    * Nonparametric age/sex-adjusted z-scores (ROI-wise kernel smoothing)
    * Polynomial lifespan models; z-score normalization to ICV; HAVAs computation
    * wBLR normative modelling
    * LOESS
    * HBLM; HBGPM

  * Notes: Algorithm used 

#### 4-4. software_tool
  * Extraction Criteria: Tool or library used
  * Data Type: string
  * Example: 
    * PCNtoolkit
    * MINT
    * PCNtoolkit v0.18
    * PCNToolkit v0.20
    * R (gamlss package)
    * PyMC3
    * AssemblyNet; custom HAVAs code
    * Stan
    * R v4.0.3 (PBSI computed in R; VR via metafor/meta)
  * Notes:
    * 

#### 4-5. response_variable
  * Extraction Criteria: Response variable
  * Data Type: string
  * Example: 
    * Brain volume, Cortical thickness
    * Cortical thickness (148 Destrieux regions)
    * Subcortical volumes
    * FA; Free-water adjusted FA (FAt); Free-water (FW) across 18 ROIs (17 tracts + WM skeleton)
    * Brain volumes: hippocampus; amygdala; inferior lateral ventrice and HAVAs
    * R1; R2*; Magnetic susceptibility (Normalized by intracranial brain volume.)
    * Grey matter volume; White matter volume; Subcortical grey matter volume; Ventricular volume; Total surface area; Mean cortical thickness; Regional volumes (Desikan–Killiany)
    * Global sGMV; WMV; Ventricular volume; Regional cortical thickness; Cortical volume; Surface area; Mean CT; Total Sa; total GMV
    * SA; CT; SV (ICV not used in PBSI)
  * Notes: 
    * The variable being modeled (e.g., Brain volume (GMV, WMV), Cortical thickness, IDPs, FA, MD, resting-state functional connectivity)
    
#### 4-6. predictor_variables
  * Extraction Criteria: Predictor variables (semicolon separated)
  * Data Type: string
  * Example: 
    * Age, Sex, ICV, site/scanner, ethnicity
    * Age; Sex; Site/Scanner
    * Age; Sex (group-effect); Scanner/Site (group-effect)
    * Age; Sex
    * Age; Diagnosis (for pathological model)
    * Age; Sex; Hemisphere
    * Age; Sex; Site; Motion (FD); Euler number (FreeSurfer quality metric)
    * Age; Sex; Site; Scanner
    * Age; Sex; Site
  * Notes: 
    * Variables used to predict Y

#### 4-7. predictor_effects
  * Extraction Criteria: Fixed/random effects specification
  * Data Type: string
  * Example: 
    * Site and Sex are modeled as group-effects (hierarchical random effects). Age is modeled as a fixed effect.
    * Fixed effects for age and sex; random effects for study/site.
    * Site as fixed covariate in LME; age modelled in normative step
    * NRAge・Sex＝fixed、Study/Site＝random
    * Site: random effect; Age/Sex: fixed; GP term for age (HBGPM)
  * Notes: 
    * If the paper specifies whether it was modelled as a fixed effect or a random effect, record that as well

#### 4-8. nm_vldtn_handle_ns
  * Extraction Criteria: Normative Modeling Validation with Handling Nuisance Structure
  * Data Type: string
  * Example: 
    *
  * Notes: 
     * 用いるデータにおけるナイサンス構造（平均ずれ、分散の違い、相関構造、非線形性など）を適切に扱っているかの判断に役立つ要約文。

#### 4-9. nm_vldtn_same_domain_nonindep
  * Extraction Criteria: Normative Modeling Validation Strategy using Same Domain Non-Independent Dataset
  * Data Type: string
  * Example:
    * 
  * Notes: 
    * 同一ドメイン（学習データセット）内の非独立データのNormative Modelの妥当性評価を行っているかの判断に役立つ要約文。
    
#### 4-10. nm_vldtn_same_domain_indep
  * Extraction Criteria: Normative Modeling Validation Strategy using Same Domain Independent Dataset
  * Data Type: string
  * Example:
    *   
  * Notes: 
    * 同一ドメイン（学習データセット）内の独立データでのNormative Modelの妥当性評価を行っているかの判断に役立つ要約文。

#### 4-11. nm_vldtn_diff_domain
  * Extraction Criteria: Normative Modeling Validation Strategy using Different Domain Dataset
  * Example:
    * 
  * Notes: 
    * 異なるドメイン（非学習データセット）のデータでのNormative Modelの妥当性評価を行っているかの判断に役立つ要約文。


### 5. Clinical Application & Analysis

#### 5-1. clinical_dataset
  * Extraction Criteria: Name of the dataset used for clinical application
  * Example: 
    * ENIGMA CHR-P
    * ABIDE
    * ASD participants of ABIDE I and II
    * Aggregated dataset from >100 studies (including ADNI, UK Biobank, ABCD, ENIGMA subsets, OpenNeuro, HCP, etc.)
    * UCL PD; UCL/NACC DLB
    * HCP Early Psychosis
    * ADNI for sMCI,  AIBL and MIRIAD for AD, ADNI for pMCI
    * CNP; HCPEP; OASIS3; TOP


#### 5-2. diseases_studied
  * Extraction Criteria: Target diseases (semicolon separated)
  * Example: 
    * Alzheimer's Disease (AD)
    * Schizophrenia (SCZ)
    * Clinical high risk for psychosis (CHP)
    * Autism spectrum disorder (ASD)
    * Attention Deficit Hyperactivity Disorder (ADHD)
    * Anxiety/Phobia disorders (APD)
    * Parkinson's disease (PD)
    * Dementia with Lewy bodies (DLB)
    * Early Psychosis (EP)
    * Schizophrenia (SZ)
    * Bipolar Disorder (BD) 
    * Early Psychosis (EP)
    * Mild Cognitive Impairment (MCI)
    * Dementia (DM)
    * Major Depressive Disorder (MDD)

  * Exampleに示されているここに示されていない疾患についてはフルスペリングして示す。
  

#### 5-3. clinical_groups_n
  * Extraction Criteria: Sample size of clinical groups
  * Example: 
    * CHR-P:1579
    * ASD:482
    * MCI:89; Dementia:90
    * PD:108; DLB:61
    * AD:987; MCI:450; LMCI:557; SCZ:1033; MDD:5250; ADHD:1214; ASD:2009; Anxiety/Phobia:1628; BD:208
    * EP:118
    * SZ:216; BD:242; ADHD:41; EP:123; MCI:51; DM:271; MDD:31; others:107



#### 5-4. clinical_groups_age
  * Extraction Criteria: Age of clinical groups (mean±SD and/or min-max)
  * Example: 
    * Mean:20.63 (SD:4.60)
    * 16.7 ± 6.4 years
    * ASD overall: 16.12±9.0 years; ASD males: 16.32±9.09; ASD females: 15.06±8.43
    * ADHD: 11-13 years; ASD: 12-13 years; SCZ: 28-35 years; MDD: 38-41 years; AD/MCI: 64-75 years; BD: NR (age data not available in demographics tables)
    * PD:64.1±7.8; DLB:73.8±6.5
    * SZ 17–69, BD 17–69, ADHD 21–50, EP 17–36, MCI   43–97, DM 43–97, MDD 17–69, others 17–69 (The exact minimum and maximum values ​​within the groups are not reported, so they are surrogated by the dataset range.)

    


#### 5-5. clinical_groups_sex
  * Extraction Criteria: Sex breakdown of clinical groups
  * Example: 
    * F:13% M:87%
    * ADHD: F48-52% M48-52%; ASD: F51% M49%; SCZ: F39% M61%; MDD: F67% M33%; AD/MCI: F50-71% M29-50%; BD: F53% M47%
    * PD: F49% M51%; DLB: F45% M55%
  * EP:F37% M63%


#### 5-6. deviation_metric
  * Extraction Criteria: How deviation from the norm was quantified
  * Example: 
    * Z-score (PBSI-Z), threshold >1.5 SD
    * Percentile score
    * MAE
    * w-score (per-region; >2 SD threshold for atypicality)
    * Centile score
    * Total outlier count
    * Z-score (FA deviations |Z|>2.6 for extremes)
    * Z-score and distance from lifespan curves; HAVAs composite
    * Z-score; Abnormal Probability Index (Pabn)


#### 5-7. association_analysis
  * Extraction Criteria: Statistical analysis used to link deviation to clinical variables. Statistical linking of deviation metrics to clinical indicators, including "what" and "to what extent" (effect summary) 
  * Example: 
    * Predictive performance metrics; group comparisons (ASD vs HC)
    * Welch's t-tests; multivariable regression (adjusted for age, sex)
    * Linear mixed effects including site/sex/age/FD/Euler; Spearman correlations with ADOS, SRS, SCQ, AQ, FIQ
    * Case-control differences in centile scores were analysed with a bootstrapped non-parametric generalization of Welch's one-way ANOVA. Pairwise comparisons were conducted using non-parametric Monte Carlo permutation tests.
    * Linear regressions adjusting for age and sex were used to test associations between total outlier count and composite cognitive score, MoCA and visuo-perception, measured using the Hooper Visual Organisation Test. In exploratory analyses, we tested associations with
disease-specific measures including global measure of severity (MDS-UPDRS), motor severity (MDS-UPDRS-III), hallucination severity (UM-
PDHQ) and depression score (HADS).
    * Multi-view CCA mapping symptoms to deviations
    * Diagnostic classification; prognostic evaluation
    * Region-wise Area Under the ROC Curve (AUC) to evaluate the predictive power of deviations for each diagnosis, with permutation tests and FDR correction for significance.



#### 5-8. key_findings_brief
  * Extraction Criteria: One-sentence summary of main results. Very brief summary of the main results and author's conclusions.


#### 5-9. key_findings_detailed
  * Extraction Criteria: Detailed results summary (including numerical values). One-paragraph Summary: Brief summary of the main results and author's conclusions, including specific numerical values where appropriate

#### 5-10. key_limitations
  * Extraction Criteria: Brief summary of the limitations

#### 5-11. application_notes
  * Extraction Criteria: Any other notable techniques or innovations in the application/analysis phase
  * Example:
    * Authors suggest limited immediate clinical application, but future utility may lie in stratifying subgroups with marked PBSI deviations and testing associations with other outcomes (functioning, persistence, non-psychotic psychopathology).


### 6. General Notes
  * Extraction Criteria: Important information not captured above. Describe any other important information not captured in the fields above.
  

-------------------

## 評価の基本原則

`3. Reference Cohort & Imaging`, `4. Normative Modeling` については以下のルールに従って回答を作ってください。

1. 各質問には **回答カテゴリー**（「Yes」「Partial」「No」「NA」）を選んでください。
   - **Yes**: 明確に記載され、基準を満たしている。
   - **Partial**: 記載はあるが不十分または不完全。
   - **No**: 情報が存在しない、または利用できない。
   - **NA**: この研究には適用されない。
2. 各回答には **信頼度（Confidence Rating）** を付してください（「High」「Medium」「Low」）。
   - **High**: 文章中に明確で直接的な記載がある。
   - **Medium**: 間接的または限定的な証拠がある（例：補足資料に記載）。
   - **Low**: 記載が曖昧または不足しており判断が不確実。
3. 回答が「Partial」または「No」の場合は、標準化された **否定的根拠カテゴリー（Negative Answer Category）** を記入してください。
   - **Missing**: 必要な情報がどこにも記載されていない。
   - **Unclear/Incomplete**: 記載はあるが不明瞭または詳細が不足。
   - **Not Negative**: 主となる回答が「Yes」または「NA」のとき。
4. 「Yes」「Partial」「No」を選んだ場合は必ず以下を記載してください：
   - **Reason:** 判断の理由を段階的に説明
   - **Supporting Text:** 判断根拠とした原文の記述を抜粋
   - **Page/Line:** 該当箇所のページ番号と行番号
5. 記述が不明確または記載がない場合は、保守的に評価し、多くの場合「No」を選択してください。

### 例

```
#### nm_vldtn_handle_ns
- Answer:
- Confidence Rating:
- Negative Answer Category: 
- Reason: 
- Supporting Text:
- Page/Line:
```

-------------------

## Important Keyword Definitions
- **Explained Variance (EV)**: Indicates how well the model captures the central tendency of the data. Values closer to 1 indicate better model performance
- **Mean Standardized Log-Loss (MSLL)**: Evaluates both central tendency and variance fit of the model. More negative values indicate better model performance
- **Skewness**: Evaluates the shape of the distribution of deviation scores (z-scores) calculated by the model. Ideally close to 0
- **Kurtosis**: Also evaluates the shape of deviation score distribution. Ideally close to 0; values greater than 0 indicate "heavy tails"
- **Pearson Correlation Coefficient (RHO)**: Indicates the strength of linear relationship between observed and predicted values. Values closer to 1 indicate better model performance
- **dMRI**: Diffusion-weighted magnetic resonance imaging
- **sMRI**: Structural magnetic resonance imaging. For example, T1 weighted MRI, T2 weighted MRI
- **fMRI**: Functional magnetic resonance imaging
- **GAMLSS**: Generalized Additive Models for Location, Scale and Shape. A statistical modeling framework that allows modeling not only the mean (location) but also variance (scale) and shape parameters as functions of covariates. Example: log σ ~ s(age) estimates σ with splines assuming variance changes with age
- **HBR**: Hierarchical Bayesian Regression. A Bayesian modeling approach that incorporates hierarchical structure with random effects. Example: site-specific scale (random effects) estimates site-specific scale differences with hierarchical Bayesian random effects
- **GPR**: Gaussian Process Regression.
- **Nuisance Structure (NS)**: 研究の主目的ではないが観測値に系統的な影響を与える要因と、その影響の入り方（平均ずれ、分散の違い、相関構造、非線形性など）の総称。NM/脳画像での具体例: サイト・スキャナ・撮像プロトコル差（加法/乗法効果、階層分散）; 画質・モーション・SNR・QC指標（測定誤差や外れ値生成）; 年齢の非線形性、性別やICV（主目的でなければナイサンス）; 縦断・家族/同胞・クラスター（被験者内/サイト内相関）; 期間/バッチ/担当者差（ドリフト、バッチ効果）; ヘテロスケダスティシティ（分散が年齢/サイトで変わる）; 空間・時間自己相関、欠測機構の偏り
- **Locally Estimated Scatterplot Smoothing (LOESS)**: Locally Weighted Scatterplot Smoothing や  Local Polynomial Regression fitting procedure とも呼ばれる。
-------------------

## 抽出結果の出力
- ファイル形式
  - json形式。jsonファイルの構造は `./DE_Template.json`を真似てください。
- ファイル名
  - あなたがClaude Codeなら `DE_Bethlehem2022_by_claude_202509191115.json`のように「DE_AuthorYear_by_claude_YYYYMMDDhhdd.拡張子」としてください。
  - あなたがGemini CLIなら `DE_Bethlehem2022_by_gemini_202509191115.json`のように「DE_AuthorYear_by_gemini_YYYYMMDDhhdd.拡張子」としてください。
  - あなたがCodex-CLIなら `DE_Bethlehem2022_by_codex_202509191115.json`のように「DE_AuthorYear_by_claude_YYYYMMDDhhdd.拡張子」としてください。
- 出力先
  - `./DE_by_AI/`

- 既に類似のファイルが出力先にあったとしても、その類似ファイルは参照せずに新たに生成してください。

